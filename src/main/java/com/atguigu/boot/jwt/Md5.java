package com.atguigu.boot.jwt;

import com.atguigu.boot.utils.AESUtil;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/11 14:31
 **/
public class Md5 {
    public static void main(String[] args) {

        String qyy = AESUtil.encrypt("111111");
        System.out.println(qyy);

        // 前台传输,加密解密
        String encrypt = AESUtil.encrypt("123456");
        System.out.println( "加密前 123456" + "加密后" + encrypt);
        String decrypt = AESUtil.decrypt(encrypt);
        System.out.println(decrypt);

        String password = DigestUtils.md5Hex("admin");
        System.out.println(password);




    }
}
