package com.atguigu.boot.quartz.service;


import com.atguigu.boot.quartz.dto.SaveJobAndTriggerDTO;
import com.atguigu.boot.quartz.entity.JobAndTriggerVo;

import java.util.List;

public interface QuartzService {
    /**
     * 新增一个定时任务
     */
    void addJob(SaveJobAndTriggerDTO jobAndTriggerVo) throws Exception;

    /**
     * 暂停定时任务
     */
    void pauseJob(String Jname, String Jgroup) throws Exception ;


    /**
     * 暂停所有定时任务
     */
    void pauseAllJob() throws Exception;

    /**
     * 继续定时任务
     */
    void resumeJob(String Jname, String Jgroup) throws Exception;

    /**
     * 恢复所有定时任务
     */
    void resumeAllJob() throws Exception;

    /**
     * 删除定时任务
     */
    void deleteJob(String Jname, String Jgroup) throws Exception;

    /**
     * 立即执行一个job
     */
    public void runAJobNow(String jobName, String jobGroupName) throws Exception;

    /**
     * 获取所有计划中的任务列表
     */
    public List<JobAndTriggerVo> list(String jobName) throws Exception;

    /**
     * 修改
     */
    public void updateJob(String triggerName, String triggerGroupName, String cronExpression)  throws Exception;


}