package com.atguigu.boot.quartz.service.impl;


import com.atguigu.boot.quartz.dto.SaveJobAndTriggerDTO;
import com.atguigu.boot.quartz.entity.JobAndTriggerVo;
import com.atguigu.boot.quartz.mapper.JobMapper;
import com.atguigu.boot.quartz.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private JobMapper jobMapper;




    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    @Override
    public List<JobAndTriggerVo> list(String  jobName) {
        return jobMapper.list(jobName);
    }


    /**
     * 新增一个定时任务
     */
    @Override
    public void addJob(SaveJobAndTriggerDTO vo) throws Exception{
        Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(vo.getJobClassName());
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withDescription(vo.getJobDescription())
                .usingJobData(vo.getJobDataMap()) //传递数据
                .withIdentity(vo.getJobName(), vo.getJobGroupName())
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(vo.getTriggerName(), vo.getTriggerGroupName())
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(vo.getCronExpression()))
                .build();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);


    }

    /**
     * 暂停定时任务
     * @param Jname 任务名
     * @param Jgroup 任务组
     */
    @Override
    public void pauseJob(String Jname, String Jgroup) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(Jname, Jgroup));
    }

    /**
     * 暂停所有定时任务
     */
    @Override
    public void pauseAllJob()  throws Exception{
        scheduler.pauseAll();
    }

    /**
     * 继续定时任务
     * @param Jname 任务名
     * @param Jgroup 任务组
     */
    @Override
    public void resumeJob(String Jname, String Jgroup)  throws Exception{
        scheduler.resumeJob(JobKey.jobKey(Jname, Jgroup));
    }

    /**
     * 继续所有定时任务
     */
    @Override
    public void resumeAllJob()  throws Exception{
        scheduler.resumeAll();

    }

    /**
     * 删除定时任务
     * @param Jname 任务名
     * @param Jgroup 任务组
     */
    @Override
    public void deleteJob(String Jname, String Jgroup)  throws Exception{
        scheduler.deleteJob(JobKey.jobKey(Jname, Jgroup));
    }

    /**
     * 立即执行一个job
     *
     * @param jobName
     * @param jobGroupName
     */
    public void runAJobNow(String jobName, String jobGroupName)  throws Exception{
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        scheduler.triggerJob(jobKey);

    }


    /**
     * 更新定时任务
     * @param triggerName
     * @param triggerGroupName
     * @param cronExpression
     * @return
     * @throws Exception
     */
    public void updateJob(String triggerName, String triggerGroupName, String cronExpression)  throws Exception{

        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            return;
        }

        String oldTime = trigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(cronExpression)) {
            /** 方式一 ：调用 rescheduleJob 开始 */
            // 触发器
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            // 触发器名,触发器组
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            // 触发器时间设定
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression));
            // 创建Trigger对象
            trigger = (CronTrigger) triggerBuilder.build();
            // 方式一 ：修改一个任务的触发时间
            scheduler.rescheduleJob(triggerKey, trigger);
            /** 方式一 ：调用 rescheduleJob 结束 */

            /** 方式二：先删除，然后在创建一个新的Job  */
            //JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
            //Class<? extends Job> jobClass = jobDetail.getJobClass();
            //removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
            //addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);
            /** 方式二 ：先删除，然后在创建一个新的Job */
        }
    }
}