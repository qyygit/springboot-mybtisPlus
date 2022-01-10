package com.atguigu.boot.common.dozer;

import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * DozerBeanConfig
 *
 * @author yaodd
 * @date 2021/7/6
 */
//@Configuration
public class DozerBeanConfig {

    @Autowired
    private Mapper mapper;


    @Bean
    public DozerUtils dozerUtils(){
        return new DozerUtils(mapper);
    }


}
