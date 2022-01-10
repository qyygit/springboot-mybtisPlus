package com.atguigu.boot.common.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * R
 * 统一返回结果
 * @author yaodd
 * @date 2020/7/3
 */
@Data
public class R {

    public static final int SUCCESS_CODE = 0;

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    /**
     * 状态码 0 成功，1 失败
     */
    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<>();



    /**
     * data
     *  传token  :token
     *  传对象 ： data
     *  传集合 ： list
     */

    public R(){}

    public R(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static R success() {

        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMessage(ResultEnum.SUCCESS.getMessage());

        return r;
    }

    public static R success(Map<String,Object> data){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMessage(ResultEnum.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }

    public R data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String,Object> data){
        this.setData(data);
        return this;
    }



    /*public static <E> R<E> success(E data) {
        R r = R.setResult(ResultEnum.SUCCESS);
        if (data instanceof String){
            r.data.put("token",data);
        }else if(data instanceof  java.util.List){
            r.data.put("list",data);
        }else if(data instanceof IPage){
            r.data.put("list",((IPage) data).getRecords());
            r.data.put("count",((IPage) data).getTotal());
        }else{
            r.data.put("data",data);
        }
        return r;
    }*/


    public static R error(){
        return R.setResult(ResultEnum.ERROR);
    }

    public static R error(String message){
        R r = R.setResult(ResultEnum.ERROR);
        r.setMessage(message);
        return r;
    }


    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }


    // 设置结果，形参为结果枚举
    public static R setResult(ResultEnum result) {
        R r = new R();
        r.setSuccess(result.getSuccess());
        r.setCode(result.getCode());
        r.setMessage(result.getMessage());
        return r;
    }
}