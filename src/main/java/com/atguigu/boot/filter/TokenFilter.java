//package com.atguigu.boot.filter;
//
//import com.atguigu.boot.constants.SystemConstants;
//import com.atguigu.boot.jwt.JwtUtils;
//import com.atguigu.boot.utils.ResponeUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * LoginFilter
// * 认证token过滤器
// * @author yaodd
// * @date 2021/7/8
// */
//@Component
//public class TokenFilter implements GlobalFilter, Ordered {
//
//
//
//
//    /**
//     * 过滤器认证token
//     * @param exchange
//     * @param chain
//     * @return
//     */
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        ServerHttpRequest request = exchange.getRequest();
//
//        String path = request.getPath().toString();
//
//        if(DoChainUrl.IGNORE_PATH_LIST.stream().anyMatch(p -> path.contains(p))){
//            return chain.filter(exchange);
//        }
//        ServerHttpResponse response = exchange.getResponse();
//        HttpHeaders headers = request.getHeaders();
//
//        String uuid = headers.getFirst(SystemConstants.AUTHORIZE_UUID);
//        if(StringUtils.isNotEmpty(uuid)){
//            //大屏认证
//            return  this.filterLargeScreen(exchange, chain,uuid);
//        }else{ //其他平台认证
//            String token = headers.getFirst(SystemConstants.AUTHORIZE_TOKEN);
//            if (StringUtils.isEmpty(token)) {
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                DataBuffer buffer = ResponeUtils.getAuthenticationResponeBuffer(response);
//                return response.writeWith(Mono.just(buffer));
//            }
//            return  this.filterLogin(exchange,chain,token);
//        }
//    }
//
//    //pc认证  手持机安卓认证
//    public Mono<Void> filterLogin(ServerWebExchange exchange, GatewayFilterChain chain,String token) {
//        ServerHttpResponse response = exchange.getResponse();
//        if (!JwtUtils.checkToken(token)) {
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);
//            DataBuffer buffer = ResponeUtils.getAuthenticationResponeBuffer(response);
//            return response.writeWith(Mono.just(buffer));
//        }
//        return chain.filter(exchange);
//    }
//    //大屏认证
//    public Mono<Void> filterLargeScreen(ServerWebExchange exchange, GatewayFilterChain chain,String uuid) {
//        ServerHttpResponse response = exchange.getResponse();
////        看设备列表中 类型为 大屏 的  有没有这种 uuid
//        return chain.filter(exchange);
//    }
//
//
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//
//    public static void main(String[] args) {
////        Date date = new Date();
////        long time = date.getTime();
////        System.out.println(time);
////        String encrypt = AESUtil.encrypt(String.valueOf(time));
////        System.out.println(encrypt);
////        String decrypt = AESUtil.decrypt(encrypt);
////        System.out.println(decrypt);
////        Date date1 = new Date(Long.parseLong(decrypt));
////
////        System.out.println(date1);
//
//    }
//}
