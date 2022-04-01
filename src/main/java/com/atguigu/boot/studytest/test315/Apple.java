package com.atguigu.boot.studytest.test315;

public class Apple {
    private int num;
    private String color;

    public Apple(int num) {
        this(num, "蓝色");
    }

    public Apple() {
    }

    // Call to 'this()' must be first statement in constructor body
    public Apple(int num, String color) {
        this.num = num;
        try {
            this.color = color;
        } catch (Exception e) {
            e.getMessage();
            e.getLocalizedMessage();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Apple apple = new Apple();
        String simpleName = apple.getClass().getSimpleName();
        System.out.println(simpleName);

        try {
            int i = 1/0;
        } finally {
            System.out.println("try .... finally");
        }
    }

}