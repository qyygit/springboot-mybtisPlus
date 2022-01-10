package com.atguigu.boot.mybatis;

import com.atguigu.boot.common.entity.Entity;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2021/9/27 15:35
 **/
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //创建时间自动加载
        log.info("start insert fill.....");
        this.setFieldValByName(Entity.CREATE_TIME, LocalDateTime.now(), metaObject);
        this.setFieldValByName("gmt_create", LocalDateTime.now(), metaObject);
        this.setFieldValByName("gmt_modified",LocalDateTime.now(),metaObject);
        this.setFieldValByName("deleted", 0,metaObject);
        this.setFieldValByName("create_time", LocalDateTime.now(), metaObject);
        this.setFieldValByName("update_time",LocalDateTime.now(),metaObject);
        this.setFieldValByName("deleted", 0,metaObject);
        this.setFieldValByName(Entity.DELETED, 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmt_modified",LocalDateTime.now(),metaObject);
    }
}
