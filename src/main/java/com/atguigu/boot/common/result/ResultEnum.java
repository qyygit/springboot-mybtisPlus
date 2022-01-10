package com.atguigu.boot.common.result;


import lombok.Getter;

/**
 * ResultEnum
 *
 * @author yaodd
 * @date 2020/7/3
 */
@Getter
public enum ResultEnum {

    SUCCESS(true,0,"成功"),
    ERROR(false,1,"失败"),
    METHOD_NOT_ALLOWED(false,405,"不支持当前请求类型"),
    PARAM_EX(false,1, "参数类型解析异常"),
    ILLEGALA_ARGUMENT_EX(false,1 ,"无效参数异常"),
    NULL_POINT_EX(false,1, "空指针异常"),
    MEDIA_TYPE_EX(false,1, "请求类型异常"),
    ;

    // 响应是否成功
    private Boolean success;
    private Integer code;
    private String message;

    ResultEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}