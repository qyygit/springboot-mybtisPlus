package com.atguigu.boot.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * TestJob
 *
 * @author yaodd
 * @date 2021/7/27
 */
@Component
public class TestJob extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // 获取参数

        //Object msg = jobDataMap.get("msg");
        LocalDateTime nowTime = LocalDateTime.now();
        System.out.println(nowTime + " - " + "测试自定义时间定时任务");

    }
}
