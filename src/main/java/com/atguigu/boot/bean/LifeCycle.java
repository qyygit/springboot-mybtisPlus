package com.atguigu.boot.bean;

public class LifeCycle {
    // 静态属性
    private static String staticField = getStaticField();

    // 静态方法快
    static {
        System.out.println(staticField);
        System.out.println("静态方法块初始化");
    }

    // 普通属性
    private String field = getField();

    //普通方法块
    {
        System.out.println(field);
    }

    //构造函数
    public LifeCycle() {
        System.out.println("构造函数初始化");
    }

    //构造函数
    public static String getStaticField() {
        String statiFiled = "Static Field Initial";
        return statiFiled;
    }

    //构造函数
    public static String getField() {
        String filed = "Field Initial";
        return filed;
    }

    // 主函数
    public static void main(String[] argc) {
        new LifeCycle();
    }
}