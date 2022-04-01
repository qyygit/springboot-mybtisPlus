package com.atguigu.boot.bean;

public class Apple {
    int i = 0;

    Apple eatApple() {
        i++;
     System.out.println(i);
        return this;
    }

    public static void main(String[] args) {
        Apple apple = new Apple();
        apple.eatApple().eatApple().eatApple().eatApple();
    }
}