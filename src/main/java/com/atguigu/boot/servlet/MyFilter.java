package com.atguigu.boot.servlet;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter(urlPatterns={"/css/*","/images/*"}) //my
@Slf4j
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器Filter程序 init方法初始化成功");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("过滤器Filter程序 doFilter方法开始工作");
    }

    @Override
    public void destroy() {
       log.info("过滤器Filter 程序销毁 执行destory方法");
    }
}
