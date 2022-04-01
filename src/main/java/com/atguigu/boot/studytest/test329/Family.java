package com.atguigu.boot.studytest.test329;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum Family implements BaseEnum {

    IN_WMS(1,"入库申请消息"),
    OUT_WMS(2,"出库申请消息"),
    WAIT_IN_WMS(3,"等待入库消息"),
    WAIT_OUT_WMS(4,"等待出库消息"),
    SCRAP_WMS(5,"报损消息"),
    MOVE_WMS(6,"调拨消息");


    Family(int key, String value) {
        this.key = key;
        this.value = value;
    }


    @EnumValue
    private Integer key;

    @JsonValue
    private String value;
}
