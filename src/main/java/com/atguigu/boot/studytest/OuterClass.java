package com.atguigu.boot.studytest;

public class OuterClass {
    private String name;
    private int age;

    class InnerClass {
        public InnerClass() {
            name = "cxuan";
            age = 25;
        }
    }
}