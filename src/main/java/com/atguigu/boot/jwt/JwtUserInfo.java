package com.atguigu.boot.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * jwt 存储的 内容
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUserInfo implements Serializable {
    /**
     * 账号id
     */
    private String id;
    /**
     * 账号
     */
    private String account;
    /**
     * 姓名
     */
    private String name;

    /**
     * 仓库
     */
    private String wareHouseId;

    /**
     *      *  平台类型
     *      *  1 iot
     *      *  2 安卓
     *      *  3 车载
     *      *  4 大屏
     *      *  5 工具
     */
    private Integer pftype ;

    private String simi;



}
