package Chap8_listener.ServletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;
/*
 *  ServletContextListener能够对ServletContext的创建和销毁做出响应。
 *  ServletContext初始化时会调用contextInitialized()方法；
 *  ServletContext销毁时会调用contextDestroyed()方法。
 *
 *  两个方法的参数都为ServletContextEvent，它可以调用方法getServletContext()获取到ServletContext
 */

@WebListener
public class DemoServletContextListener implements ServletContextListener {
    //ServletContext创建时执行
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext=servletContextEvent.getServletContext();
        Map<String,String> countries=new HashMap<>();
        countries.put("cn","China");
        countries.put("us","United States");
        //在ServletContext刚创建时，将一个countries的Map放置其中
        servletContext.setAttribute("countries",countries);
    }

    //ServletContext销毁时执行
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
