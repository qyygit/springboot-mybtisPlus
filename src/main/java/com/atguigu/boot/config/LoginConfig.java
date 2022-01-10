//package com.atguigu.boot.config;
//
//import com.atguigu.boot.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////@EnableWebMvc
//@Configuration
//public class LoginConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/login","/css/**","/fonts/**","/images/**",
//                        "/js/**","/aa/**","/sql"); //放行的请求
//    }
//
//}
