package com.chengqj.springbootbasic.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//通过注解整合Filter
@WebFilter(filterName = "FirstFilter",urlPatterns = {"*.do","/First"})
public class FirstFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入filter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("离开filter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
