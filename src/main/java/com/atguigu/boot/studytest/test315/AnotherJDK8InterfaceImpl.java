package com.atguigu.boot.studytest.test315;

public class AnotherJDK8InterfaceImpl implements JDK8Interface {
      
    // 签名跟接口default方法一致,但是不能再加default修饰符  
    @Override  
    public void defaultMethod() {  
        System.out.println("接口实现类覆盖了接口中的default");  
    }  
}  
