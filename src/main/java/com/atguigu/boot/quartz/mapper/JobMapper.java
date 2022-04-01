package com.atguigu.boot.quartz.mapper;

import com.atguigu.boot.quartz.entity.JobAndTriggerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * create by yaodd on 2020/8/24
 */
@Mapper
public interface JobMapper {

    List<JobAndTriggerVo> list(@Param("jobName") String jobName);

}