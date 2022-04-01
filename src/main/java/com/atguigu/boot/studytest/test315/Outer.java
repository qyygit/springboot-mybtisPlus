package com.atguigu.boot.studytest.test315;

class Outer {
    private String info = "我爱你中国";

    class Inner {
        public void print() {
            System.out.println(info);
        }
    }

    public void fun() {
        new Inner().print();
        Object[] list = new Object[10];
    }


}