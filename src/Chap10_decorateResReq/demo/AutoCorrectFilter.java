package Chap10_decorateResReq.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 在应用的每个servlet中，删除多余的空格。
 */

//@WebFilter(filterName = "AutoCorrectFilter",urlPatterns = "/*")
public class AutoCorrectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        AutoCorrectHttpServletRequestWrapper wrapper=new AutoCorrectHttpServletRequestWrapper(httpServletRequest);
        filterChain.doFilter(wrapper,servletResponse);
    }

    class AutoCorrectHttpServletRequestWrapper extends HttpServletRequestWrapper{
        private HttpServletRequest httpServletRequest;
        public AutoCorrectHttpServletRequestWrapper(HttpServletRequest httpServletRequest){
            super(httpServletRequest);
            this.httpServletRequest=httpServletRequest;
        }

        public String getParameter(String name){
            return "重写方法";
        }

        //xxx


    }

    @Override
    public void destroy() {

    }
}
