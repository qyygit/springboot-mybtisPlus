package com.atguigu.boot.common.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PfTypeEnum
 * JwtUserInfo 中对应的平台类型
 *
 * @author yaodd
 * @date 2021/10/9
 */
@Getter
public enum PfTypeEnum {

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

    PfTypeEnum( Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    public static String getNameByCode( Integer code) {
        if (code == null) {
            return "";
        }
        PfTypeEnum[] enums = PfTypeEnum.values();
        for (PfTypeEnum  e : enums ) {
            if (code.equals(e.getCode())) {
                return e.name();
            }
        }
        return "";
    }

    public static List<Map<String, Object>> getdeviceStatusEnumList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (PfTypeEnum deviceStatusEnum: PfTypeEnum.values()) {
            Map item= new HashMap<String, Object>();
            item.put("code",deviceStatusEnum.code);
            item.put("name",deviceStatusEnum.name);
            list.add(item);
        }
        return list;
    }

}

