//package com.atguigu.boot.utils.file;
//
//import com.soma.constants.SystemConstants;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 通用配置类
// *
// * @author lizhi
// */
//@Configuration
//public class ResourcesConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 注册本地文件上传路径
//        registry.addResourceHandler(SystemConstants.RESOURCE_PREFIX + "/**")
//                .addResourceLocations("file:" + FileConfig.getFileBasePath());
//    }
//}
