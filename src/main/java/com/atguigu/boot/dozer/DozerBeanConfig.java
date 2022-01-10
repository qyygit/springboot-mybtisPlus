package com.atguigu.boot.dozer;

import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DozerBeanConfig
 *
 * @author yaodd
 * @date 2021/7/6
 */
@Configuration
public class DozerBeanConfig {

    @Autowired
    private Mapper mapper;


    @Bean
    public DozerUtils dozerUtils(){
        return new DozerUtils(mapper);
    }


}
