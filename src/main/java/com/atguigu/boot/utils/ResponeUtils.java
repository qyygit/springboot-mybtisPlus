package com.atguigu.boot.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.nio.charset.StandardCharsets;

/**
 * ResponeUtils
 *
 * @author yaodd
 * @date 2021/7/9
 */
public class ResponeUtils {

    public static DataBuffer getAuthenticationResponeBuffer(ServerHttpResponse response){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",false);
        jsonObject.put("code",1000);
        jsonObject.put("message","用户未认证！");
        //把json对象转换成字节数组
        byte[] bits = jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8);
        //把字节数据转换成一个DataBuffer
        DataBuffer buffer = response.bufferFactory().wrap(bits);

        return buffer ;


    }
    public static DataBuffer getAuthorizationResponeBuffer(ServerHttpResponse response){

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success",false);
        jsonObject.put("code",2);
        jsonObject.put("message","用户未授权！");
        //把json对象转换成字节数组
        byte[] bits = jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8);
        //把字节数据转换成一个DataBuffer
        DataBuffer buffer = response.bufferFactory().wrap(bits);

        return buffer ;


    }

}
