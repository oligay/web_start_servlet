package Chap9_filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
/*
 *  一个简单的Filter，把Request请求的URL记录到日志文本文件中。
 *
 *  日志文本文件名通过Filter的初始化参数来配置。
 *  日志的每条记录都有一个前缀，也由Filter初始化参数来定义。
 */

//@WebFilter(filterName = "LoggingFilter",urlPatterns = "/*",initParams = {
//        @WebInitParam(name = "logFileName",value = "log.txt"),
//        @WebInitParam(name = "prefix",value = "URI: ")
//})
public class LoggingFilter implements Filter {
    private PrintWriter logger;
    private String prefix;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        prefix=filterConfig.getInitParameter("prefix");
        String logFileName=filterConfig.getInitParameter("logFileName");
        String path=filterConfig.getServletContext().getRealPath("/");
        System.out.println(path);

        //若不更改path，将会在Tomcat文件夹中生成文件
        System.out.println("logFileName:"+logFileName);
        try {
            logger=new PrintWriter(new File(path,logFileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("LoggingFilter.doFilter");
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        logger.println(new Date()+" "+prefix+httpServletRequest.getRequestURI());
        logger.flush();
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroying filter");
        if (logger!=null){
            logger.close();
        }
    }
}
