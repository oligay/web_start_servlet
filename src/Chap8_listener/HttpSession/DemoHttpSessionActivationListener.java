package Chap8_listener.HttpSession;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
/*
 *  在分布式环境下，会用多个容器来进行负载均衡，有可能需要将session保存起来，在容器之间传递。
 *  当把很少用到的对象转存到其他容器上时，容器就会通知所有HttpSessionActivationListener接口的实现类。
 *
 *  方法参数为HttpSessionEvent对象，可以通过其获得HttpSession
 */

@WebListener
public class DemoHttpSessionActivationListener implements HttpSessionActivationListener {
    //HttpSession将要失效时，容器会调用方法
    @Override
    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {

    }

    //HttpSession被转移到其他容器之后，方法会被调用
    @Override
    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {

    }
}
