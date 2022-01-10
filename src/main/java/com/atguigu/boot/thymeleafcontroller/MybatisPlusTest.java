package com.atguigu.boot.thymeleafcontroller;

import com.atguigu.boot.mapper.UserMapper;
import com.atguigu.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2021/12/29 17:18
 **/
@RequestMapping("/myabtisPlus")
public class MybatisPlusTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;



}
