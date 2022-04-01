package com.atguigu.boot.studytest.test329;

import static com.atguigu.boot.exception.code.ExceptionCode.REQUIRED_FILE_PARAM_EX;

public class EnumUse {
    public static void main(String[] args) {


        System.out.println(REQUIRED_FILE_PARAM_EX.getMsg());
        Family s = Family.IN_WMS;
        System.out.println(s);

    }
}