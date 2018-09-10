package Chap8_listener.ServletRequest;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
/*
 *  监听器会对ServletRequest的创建和销毁事件进行响应。
 *  容器会通过一个池来存放并重复利用多个ServletRequest，创建就是从容器池里被分配出来的时刻开始；销毁时刻就是放回容器池里的时间。
 *  方法的参数都是一个ServletRequestEvent对象，可以用getServletRequest()方法获取ServletRequest对象，也可以getServletContext()获取ServletContext对象。
 */

@WebListener
public class DemoServletRequestListener implements ServletRequestListener {
    //当ServletRequest销毁时，方法被调用
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest=servletRequestEvent.getServletRequest();
        Long start= (Long) servletRequest.getAttribute("start");
        Long end=System.nanoTime();
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        String uri=httpServletRequest.getRequestURI();
        System.out.println("time taken to execute "+uri+":"+((end-start)/1000)+"microseconds");
    }

    //当一个ServletRequest创建时，方法会被调用
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest=servletRequestEvent.getServletRequest();
        servletRequest.setAttribute("start",System.nanoTime());
    }
}
