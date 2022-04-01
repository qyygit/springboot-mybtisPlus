package com.atguigu.boot.studytest.test315;

public class Main {
    public static void main(String[] args) {  
        // static方法必须通过接口类调用  
        JDK8Interface.staticMethod();  
  
        //default方法必须通过实现类的对象调用  
        new AnotherJDK8InterfaceImpl().defaultMethod();
    }  
}