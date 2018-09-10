package Chap8_listener.ServletRequest;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
/*
 *  和之前的xxxAttributeListener类似，对应于ServletRequest范围的属性被添加、删除或替换时，接口会被调用。
 *  方法的参数为ServletRequestAttributeEvent对象。
 */

public class DemoServletRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {

    }
}
