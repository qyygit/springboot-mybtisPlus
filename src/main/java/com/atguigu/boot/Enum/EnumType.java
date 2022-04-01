package com.atguigu.boot.Enum;

import lombok.Getter;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/24 10:13
 **/
@Getter
public enum EnumType {
    /**
     *      *  平台类型
     *      *  1 iot
     *      *  2 安卓
     *      *  3 车载
     *      *  4 大屏
     *      *  5 工具
     */
//    private Integer pftype ;

    IOT(1,"IOT"),
    安卓完井(2,"安卓完井"), //完井
    安卓工具(3 ,"安卓工具"),//工程技术中心
    车载(4,"车载"),
    //    车载工具(5,"车载工具"),
    大屏(6, "大屏");


    private Integer code;
    private String name;

    EnumType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    EnumType() {
    }

}
