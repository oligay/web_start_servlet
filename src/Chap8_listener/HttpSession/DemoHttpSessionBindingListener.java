package Chap8_listener.HttpSession;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
/*
 *  当有属性绑定或者解绑到HttpSession上时，监听器会被调用。
 */

@WebListener
public class DemoHttpSessionBindingListener implements HttpSessionBindingListener {
    //属性绑定时调用该方法
    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    //属性解绑时调用该方法
    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
