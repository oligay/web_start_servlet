package Chap11_async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AsyncCompleteServlet",urlPatterns = "/asyncComplete",asyncSupported = true)
public class AsyncCompleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter printWriter=response.getWriter();
        printWriter.println("<html><head><title>Async Servlet</title></head>");
        printWriter.println("<body><div id='progress'></div>");
        final AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(60000);
        //为AsyncContext手动注册一个AsyncListener
        asyncContext.addListener(new MyAsyncListener());
        asyncContext.start(() -> {
            System.out.println("new thread:"+Thread.currentThread().getName());
            for (int i = 0; i < 10; i++) {
                printWriter.println("<script>");
                printWriter.println("document.getElementById("+"'progress').innerHTML='"+(i*10)+"% complete'");
                printWriter.println("</script>");
                printWriter.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            printWriter.println("<script>");
            printWriter.println("document.getElementById('progress').innerHTML= 'DONE'");
            printWriter.println("</script>");
            printWriter.println("</body></html>");
            asyncContext.complete();
        });
    }
}
