package com.atguigu.boot.studytest.test315;

public class Vehicle {
    private Tire tire;
    private Light light;

    public Vehicle(Tire tire,Light light) {
        this.tire = tire;
        this.light = light;
    }

    public void operation() {
        light.light();
        tire.run();
    }

    public static void main(String[] args) {
        Tire tire = new Tire();
        Light light = new Light();
        Vehicle vehicle = new Vehicle(tire,light);
        vehicle.operation();
    }
}
