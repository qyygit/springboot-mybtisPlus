package com.atguigu.boot.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TestJob
 *
 * @author yaodd
 * @date 2021/7/27
 */
@Component
public class TestJob2 extends QuartzJobBean {



    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        LocalDateTime nowTime = LocalDateTime.now();
        System.out.println(nowTime + " - " + "测试定时任务");
        /*JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        String msg = (String) jobDataMap.get("msg");

        System.out.println("定时项目");
        R stringR = sapFeignClient.test1(msg);
        System.out.println(stringR.getData());*/
    }

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(format);

    }
}