//package com.atguigu.boot.servlet;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @Author: QYY
// * @Description: TODO
// * @DateTime: 2022/1/12 9:29
// **/
//
//
//@Component
//public class TokenFilter implements GlobalFilter , Ordered {
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        System.out.println("GlobalFilter 全局过滤器");
//        return null;
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
