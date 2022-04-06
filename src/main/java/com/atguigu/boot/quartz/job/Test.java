package com.atguigu.boot.quartz.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/27 11:45
 **/
@Component
public class Test {
    @Scheduled(cron = "0/3 * * * * ? ")
    protected void test2()  {

        System.out.println("测试定时任务22");
        /*JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        String msg = (String) jobDataMap.get("msg");

        System.out.println("定时项目");
        R stringR = sapFeignClient.test1(msg);
        System.out.println(stringR.getData());*/
    }
}
