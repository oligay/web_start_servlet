package Chap11_async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *  Servlet支持异步处理且其长期运行的任务就是简单地休息三秒钟。用线程名字证明在不同的线程中执行的。
 */

@WebServlet(name = "EasyAsyncDispatchServlet",urlPatterns = "/asyncDispatch",asyncSupported = true)
public class EasyAsyncDispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();

        req.setAttribute("mainThread",Thread.currentThread().getName());

        asyncContext.setTimeout(5000);
        asyncContext.start(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            req.setAttribute("workerThread",Thread.currentThread().getName());
            asyncContext.dispatch("/threadNames.jsp");
        });
    }
}
