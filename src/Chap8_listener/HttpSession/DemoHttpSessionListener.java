package Chap8_listener.HttpSession;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;
/*
 *  当一个HttpSession创建或者销毁时，容器都会通知所有的HttpSessionListener监听器。
 *  接口有两个方法：sessionCreated()和sessionDestroyed()
 *  方法的参数都是HttpSessionEvent对象，可以通过其getSession()方法获取当前的HttpSession。
 */

//统计HttpSession的数量。使用一个对象来统计，然后将这个对象保存成ServletContext范围的属性。
@WebListener
public class DemoHttpSessionListener implements HttpSessionListener, ServletContextListener {


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession=httpSessionEvent.getSession();
        ServletContext servletContext=httpSession.getServletContext();
        AtomicInteger userCounter= (AtomicInteger) servletContext.getAttribute("userCounter");
        int userCount=userCounter.incrementAndGet();
        System.out.println("userCount incremented to :"+userCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession=httpSessionEvent.getSession();
        ServletContext servletContext=httpSession.getServletContext();
        AtomicInteger userCounter= (AtomicInteger) servletContext.getAttribute("userCounter");
        int userCount=userCounter.decrementAndGet();
        System.out.println("--------------------- userCount decremented to :"+userCount);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext=servletContextEvent.getServletContext();
        //使用AtomicInteger对象来统计，保证能同步进行加减的操作
        servletContext.setAttribute("userCounter",new AtomicInteger());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
