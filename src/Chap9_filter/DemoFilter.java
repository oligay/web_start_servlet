package Chap9_filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //FilterConfig接口允许通过他的getServletContext的方法来访问ServletContext
        ServletContext servletContext=filterConfig.getServletContext();

        //配置了Filter的名字，可以用getFilterName方法获取名字
        String filterName=filterConfig.getFilterName();

        //获取初始化参数，getInitParameterNames()方法返回Filter参数名字的Enumeration对象
        Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();

        //获取初始化参数，getInitParameter(parameterName)返回对应属性值
        String xxx = filterConfig.getInitParameter("xxx");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
