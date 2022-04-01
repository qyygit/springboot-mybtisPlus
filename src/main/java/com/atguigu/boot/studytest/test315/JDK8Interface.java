package com.atguigu.boot.studytest.test315;

public interface JDK8Interface {
  
    // static修饰符定义静态方法  
    static void staticMethod() {  
        System.out.println("接口中的静态方法");  
    }

//    public  test(){
//        //接口木偶人实现,只能用static default 实现
//    }
    // default修饰符定义默认方法  
    default void defaultMethod() {
        System.out.println("接口中的默认方法");  
    }  
}  
