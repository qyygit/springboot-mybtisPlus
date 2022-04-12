package com.atguigu.boot.controller;


import com.atguigu.boot.dozer.DozerUtils;
import com.atguigu.boot.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


//import org.apache.commons.collections4.CollectionUtils;
//@ResponseBody
//@Controller


// JRebel
@Slf4j
@RestController
@RequestMapping("/demo")
public class HelloController {

    @Autowired
    protected DozerUtils dozerUtils;

//    @Autowired
//    private Car car;

//    @RequestMapping("/car")
//    public Car testCar(){
//        return car;
//    }
    @GetMapping("/hello")
    public String handle01(@RequestParam("name") String name){
            log.info("请求进来了....");
            int i = 10/0;

//        ObjectUtils.isEmpty();

        if (i>1){
                throw new BizException("全局异常处理");
            }

        return "Hello, Spring Boot 2!"+"你好："+name;
    }

//    @Autowired
//    Car car;
//
//
//    @RequestMapping("/car")
//    public Car car(){
//        return car;
//    }



}
