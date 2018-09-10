package Chap9_filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
 *  下载计数Filter —— 在Filter中计算资源下载的次数。
 *
 *  使用Executor解决多线程访问的线程安全问题
 */

public class DownloadCounterFilter implements Filter {
    ExecutorService executorService= Executors.newSingleThreadExecutor();
    Properties downloadLog;
    File logFile;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("DownloadCounterFilter");
        String path = filterConfig.getServletContext().getRealPath("/");
        logFile=new File(path,"downloadLog.txt");
        if (!logFile.exists()){
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        downloadLog=new Properties();
        try {
            downloadLog.load(new FileReader(logFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        final String uri = httpServletRequest.getRequestURI();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String property = downloadLog.getProperty(uri);
                if (property==null){
                    downloadLog.setProperty(uri,"1");
                }else {
                    int count=0;
                    count=Integer.parseInt(property);
                    count++;
                    downloadLog.setProperty(uri,Integer.toString(count));
                }
                try {
                    downloadLog.store(new FileWriter(logFile),"");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        executorService.shutdown();
    }
}
