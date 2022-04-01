package com.atguigu.boot.studytest.test318;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/3/18 13:57
 **/
public class Test01 {
        public static void main(String[] args) {
            List<String> name = new ArrayList<String>();
            List<Integer> age = new ArrayList<Integer>();
            List<Number> number = new ArrayList<Number>();
            name.add("cxuan");
            age.add(18);
            number.add(314);
            generic(name);
            generic(age);
            generic(number);
        }
        public static void generic(List<?> data) {
            System.out.println("Test cxuan :" + data.get(0));
        }
}
