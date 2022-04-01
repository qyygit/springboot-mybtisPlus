//package com.atguigu.boot.filter;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @Classname doChainUrl
// * @Description TODO
// * @Date 2021/7/30 14:45
// * @Created by guolin
// */
//public class DoChainUrl {
//
//    /**
//     * token 不认真的路径
//     */
//    public static final List<String> IGNORE_PATH_LIST =
//            Arrays.asList(
//                    "/doc.html",
//                    "/webjars",
//                    "/favicon.ico",
//                    "/swagger-resources/configuration/ui",
//                    "/swagger-resources",
//                    "/v2/api-docs",
//                    "/system/auth/login",
//                    "/sap/sap/auth/login",
//                    "/system/v2/api-docs",
//                    "/ws/iotWebSocket/",
//                    "/ws/v2/api-docs",
//                    "/system/apk/findByTime"
//            );
//
//    /**
//     * token  权限只读的
//     */
//    public static final List<String> ONLY_READ_PATH_LIST = Arrays.asList( "select","find","get","list","like");
//
//
//    public static void main(String[] args) {
//        String path="/toolManager/findBytoolCodeName";
//        if(DoChainUrl.ONLY_READ_PATH_LIST.stream().anyMatch(p -> path.contains(p))){
//            System.out.println(path);
//        }
//    }
//}
