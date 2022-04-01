package com.atguigu.boot.Enum;

import com.atguigu.boot.utils.AESUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/24 10:24
 **/
public class Test {
    public static void main(String[] args) {

        //第一步, 判断类型是否匹配
        List<Integer> enumList = Arrays.asList(EnumType.IOT.getCode(), EnumType.安卓工具.getCode(), EnumType.安卓工具.getCode(), EnumType.车载.getCode());
        Integer type = 3;
        if (enumList.contains(type)){
            System.out.println("设备类型匹配,继续执行");

            String userName = "admin";
            String passWord = "123456";
            String encrypt = AESUtil.encrypt("888888");
            System.out.println("前台加密后的密码 : " + encrypt);

            //第二步,用户名,密码校验 ,查询Mysql ,是否存在此用户
            // 模拟用户存在,继续执行,
            if (true){

                // 第三步,校验密码
                String decrypt = AESUtil.decrypt(encrypt);
                System.out.println("前台密码解密  : " + decrypt);
                //通过 MD5 加密 与 库里加密,密码比较
                String md5PassWord = DigestUtils.md5Hex(decrypt);
//                    DigestUtils.getDigest()
                System.out.println("md5 加密前 " + decrypt + " - "  + "md5 加密后 " +  md5PassWord);

                // 校验通过, 把信息存入token ,名称  id , 公司编码,  后续直接在token中获取
//                JwtUtils.getJwtToken(user);
                //
            }
        }
    }
}
