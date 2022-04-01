package com.atguigu.boot.mybatis;


import com.atguigu.boot.common.entity.Entity;
import com.atguigu.boot.common.entity.SuperEntity;
import com.atguigu.boot.common.id.IdGenerate;
import com.atguigu.boot.common.id.SnowflakeIdGenerate;
import com.atguigu.boot.constants.SystemConstants;
import com.atguigu.boot.jwt.JwtUserInfo;
import com.atguigu.boot.jwt.JwtUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2021/9/27 15:35
 **/
@Component
@Slf4j
public class MyMetaObjectHandler2 implements MetaObjectHandler {

    /**
     * id类型判断符
     */
    private final static String ID_TYPE = "java.lang.String";

    /**
     * 所有的继承了Entity、SuperEntity的实体，在insert时，
     * id： 会通过IdGenerate生成唯一ID
     * createUser, updateUser: 自动赋予 当前线程上的登录人id
     * createTime, updateTime: 自动赋予 服务器的当前时间
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


        //增加时自动创建 id 通过雪花算法获取id
        IdGenerate<Long> idGenerator = new SnowflakeIdGenerate(1);
        Long id = idGenerator.generate();
        if (ID_TYPE.equals(metaObject.getGetterType(SuperEntity.FIELD_ID).getName())) {
            this.setFieldValByName(SuperEntity.FIELD_ID, String.valueOf(id), metaObject);
        } else {
            this.setFieldValByName(SuperEntity.FIELD_ID, id, metaObject);
        }

        //创建时间自动加载
        this.setFieldValByName(Entity.CREATE_TIME, LocalDateTime.now(), metaObject);

        //创建人名称 通过request中的token获取。
        String token = request.getHeader(SystemConstants.AUTHORIZE_TOKEN);
        if (!StringUtils.isEmpty(token)) {
            JwtUserInfo jwtUserInfoByJwtToken = JwtUtils.getJwtUserInfoByJwtToken(token);
            this.setFieldValByName(Entity.CREATE_USER, StringUtils.isEmpty(jwtUserInfoByJwtToken.getName()) ? "" : jwtUserInfoByJwtToken.getName(), metaObject);
        }

        //软删除  默认不删除
        this.setFieldValByName(Entity.DELETED, 0, metaObject);
    }

    /**
     * 所有的继承了Entity、SuperEntity的实体，在update时，
     * updateUser: 自动赋予 当前线程上的登录人id
     * updateTime: 自动赋予 服务器的当前时间
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        JwtUserInfo jwtUserInfoByJwtToken = JwtUtils.getJwtUserInfoByJwtToken(request.getHeader("token"));

        if (jwtUserInfoByJwtToken != null) {
            this.setFieldValByName(Entity.UPDATE_USER, StringUtils.isEmpty(jwtUserInfoByJwtToken.getName()) ? "" : jwtUserInfoByJwtToken.getName(), metaObject);
        }

        this.setFieldValByName(Entity.UPDATE_TIME, LocalDateTime.now(), metaObject);


    }

}


