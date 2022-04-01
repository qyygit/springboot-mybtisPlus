package com.atguigu.boot.aspect;

import java.lang.annotation.*;

/**
 * @Classname WebLogAnon
 * @Description 日志注解
 * @Date 2021/7/30 15:18
 * @Created by guolin
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface WebLogAnon {
}
