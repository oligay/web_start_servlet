package Chap9_filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/*
 *  此例为图像文件保护Filter，在浏览器中输入图像文件的URL路径时防止下载图像文件。
 *  图像文件只有当图像链接在页面中被点击的时候才会显示。
 *
 *  如果HTTP Header的referer值为null，就意味着当前的请求是通过输入URL来访问该资源的。
 *  Filter应用于所有的.png,.jpg,.gif文件后缀。
 */

//@WebFilter(filterName = "ImageProtectorFilter",urlPatterns = {".png",".jpg",".gif"})
public class ImageProtectorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ImageProtectorFilter");
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        String referer = httpServletRequest.getHeader("referer");
        System.out.println("referer:"+referer);
        if (referer!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            throw new ServletException("Image not available");
        }
    }

    @Override
    public void destroy() {

    }
}
