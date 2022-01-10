package com.atguigu.boot.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@WebListener
@Slf4j
public class MySwervletContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("监听器MySwervletContextListener监听程序启动-项目初始化完成");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("监听器MySwervletContextListener监听到项目销毁");
    }
}
