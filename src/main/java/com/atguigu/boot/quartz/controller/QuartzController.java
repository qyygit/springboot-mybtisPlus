package com.atguigu.boot.quartz.controller;


import com.atguigu.boot.base.BaseController;
import com.atguigu.boot.common.result.R;
import com.atguigu.boot.quartz.dto.JobDTO;
import com.atguigu.boot.quartz.dto.SaveJobAndTriggerDTO;
import com.atguigu.boot.quartz.dto.UpdateJobAndTriggerDTO;
import com.atguigu.boot.quartz.entity.JobAndTriggerVo;
import com.atguigu.boot.quartz.job.TestJob;
import com.atguigu.boot.quartz.service.QuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("task")
@Api(tags = "定时任务控制器")
@Slf4j
public class QuartzController extends BaseController {

    @Autowired
    private QuartzService quartzService;


    @Autowired
    private Scheduler scheduler;




    @ApiOperation("测试定时任务任务功能点")
    @PostMapping("/testTime")
    public void testTime(@RequestBody JobDTO jobDTO) throws Exception {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("type", 1);//声明是pc下发   2 是遥控器指示

        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)// 定义Job类为HelloQuartz类，这是真正的执行逻辑所
                .withIdentity("noticePaly", "notice") // 定义name/group
                .usingJobData(jobDataMap)
                .build();


        //2. 定义一个Trigger，定义该job立即执行，并且每两秒钟执行一次，直到永远

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = jobDTO.getShowTime().atZone(zone).toInstant();
        Date date = Date.from(instant);


               Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "noticeTrigger")// 定义名字和组
                       .withIdentity("trigger3", "group1")
                       .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(16, 21))
                       .forJob(jobDetail)
                       .build();



        //建立一个触发器，将在下一个小时的整点触发，然后每2小时重复一次：
//        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "noticeTrigger")// 定义名字和组
//                .withIdentity("trigger8") // because group is not specified, "trigger8" will be in the default group
//                .startAt(DateBuilder.evenHourDate(null)) // get the next even-hour (minutes and seconds zero ("00:00"))
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInHours(2)
//                        .repeatForever())
//                // note that in this example, 'forJob(..)' is not called which is valid
//                // if the trigger is passed to the scheduler along with the job
//                .build();


       // 立即触发，每个2秒行一次，直到14:45:00 结束
        /*Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "noticeTrigger")// 定义名字和组
//                      次数减1  然后播放间隔 数据库单位是 分  需要转换为为秒
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
                        .repeatForever())  //立即出发事件
                .endAt(DateBuilder.dateOf(14, 45, 0))
                .build();*/


        // 每五分钟执行一次, 只执行一次
        /*Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "noticeTrigger")// 定义名字和组
                .startAt(date)//现在开始
                .startAt(futureDate(1, DateBuilder.IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY).withIntervalInSeconds(2))
                .build();*/



        // 5分钟以后开始触发，仅执行一次：
       /* trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger5", "group1")
                .startAt(futureDate(5, IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
                .forJob(myJobKey) // identify job with its JobKey
                .build();*/



        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    @ApiOperation("暂停任务")
    @PostMapping("/pause")
    public R pauseTask(@RequestBody JobDTO job) throws Exception {
        quartzService.pauseJob(job.getJname(), job.getJgroup());
        return success();
    }


    @ApiOperation("新增任务")
    @PostMapping("/insert")
    public R insertTask(@Validated @RequestBody SaveJobAndTriggerDTO jobAndTriggerVo) throws Exception {
        log.info("新增定时任务信息{}", jobAndTriggerVo);
        quartzService.addJob(jobAndTriggerVo);
        return success();
    }


    @ApiOperation("修改任务")
    @PostMapping("/update")
    public R updateJob(@Validated @RequestBody UpdateJobAndTriggerDTO jobAndTriggerVo) throws Exception {
        quartzService.updateJob(jobAndTriggerVo.getTriggerName(),
                jobAndTriggerVo.getTriggerGroupName(),
                jobAndTriggerVo.getCronExpression());
        return success();
    }


    @ApiOperation("查询全部")
    @GetMapping("/list")
    public R list(String jobName) throws Exception {
        // List<Map<String, Object>> maps = quartzService.queryAllJob();
//        LambdaQueryWrapper<JobAndTriggerVo> wrapper= new LambdaQueryWrapper<JobAndTriggerVo>().like(!StringUtils.isEmpty(jobName), JobAndTriggerVo::getJobName, jobName);
        List<JobAndTriggerVo> list = quartzService.list(jobName);
        return success().data("list", list).data("count", list.size());
    }


    @ApiOperation("立即运行")
    @PostMapping("/runAJobNow")
    public R runAJobNow(@RequestBody JobDTO job) throws Exception {
        quartzService.runAJobNow(job.getJname(), job.getJgroup());
        return success();
    }

    @ApiOperation("继续任务")
    @PostMapping("/resume")
    public R resumeTask(@RequestBody JobDTO job) throws Exception {
        quartzService.resumeJob(job.getJname(), job.getJgroup());
        return success();
    }

    @ApiOperation("删除任务")
    @PostMapping("/delete")
    public R deleteTask(@RequestBody JobDTO job) throws Exception {
        quartzService.deleteJob(job.getJname(), job.getJgroup());
        return success();
    }
}