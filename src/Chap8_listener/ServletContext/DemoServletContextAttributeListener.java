package Chap8_listener.ServletContext;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;
/*
 *  当一个ServletContext范围的属性被添加、删除或替换时，ServletContextAttributeListener接口的实现类会接收到消息。
 *
 *  三个方法都能获取到一个ServletContextAttributeEvent的对象，通过该对象的getName()和getValue()方法可以获取属性的名称和值。
 */

@WebListener
public class DemoServletContextAttributeListener implements ServletContextAttributeListener {
    //在一个ServletContext范围属性被添加时被容器调用
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {

    }

    //在一个ServletContext范围属性被删除时被容器调用
    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

    }
    //在一个ServletContext范围属性被替换时被容器调用
    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {

    }
}
