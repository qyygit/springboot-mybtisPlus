package com.atguigu.boot.i18n;


import lombok.Getter;

/**
 * ResultEnum
 *
 * @author yaodd
 * @date 2020/7/3
 */
@Getter
public enum I18nEnum {

    //common_exception
    SUCCESS("success","成功"),
    ERROR("error","失败"),
    METHOD_NOT_ALLOWED("the current request type java is not supported","不支持当前请求类型"),
    PARAM_EX("Parameter type resolution exception","参数类型解析异常"),
    ILLEGALA_ARGUMENT_EX("Invalid parameter difference","无效参数异常"),
    NULL_POINT_EX("Null pointer exception","空指针异常"),
    MEDIA_TYPE_EX("Request type exception","请求类型异常"),
    SERVICE_FAILD("Server exception, please try again later!","服务器异常，请稍后再试！"),
    DATA_EX("The date parameter can only be between 0-365 days","日期参数只能介于0-365天之间"),
    FILE_UPLOAD_EX( "Could not store file  . Please try again later!","无法存储文件。请稍后再试！"),
    SUPERENTITY_CLONE_EX( "superentity clone  fail","克隆失败！"),
    ;



    private String usMsg;
    private String chMsg;

    I18nEnum( String usMsg, String chMsg) {
        this.usMsg = usMsg;
        this.chMsg = chMsg;
    }

}