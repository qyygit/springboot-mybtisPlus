package com.atguigu.boot.studytest.test315;

public class Apple1 extends Fruit{



    public void eat() {
        this.num = 10;
        System.out.println("eat " + num + " Apple");
    }


    public static void main(String[] args) {
        Fruit fruit = new Apple1();
        fruit.eat();



    }
}