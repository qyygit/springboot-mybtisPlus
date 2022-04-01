package com.atguigu.boot.studytest.test329;

import java.lang.reflect.Constructor;

public class Hero {
    public String name; //姓名
    public float hp; //血量
    public float armor; //护甲
    public int moveSpeed; //移动速度


    public static void main(String[] args) {
        String className = "com.atguigu.boot.studytest.test329.Hero";
        try {
            //获取类对象的第一种方式
            Class pClass1 = Class.forName(className);
            Constructor[] constructors = pClass1.getConstructors();
            System.out.println(constructors[0]);
            //获取类对象的第二种方式
            Class pClass2 = Hero.class;
            //获取类对象的第三种方式
            Class pClass3 = new Hero().getClass();
            System.out.println(pClass1 == pClass2);//输出true
            System.out.println(pClass1 == pClass3);//输出true
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}