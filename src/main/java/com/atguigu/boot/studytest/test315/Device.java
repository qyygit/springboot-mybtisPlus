package com.atguigu.boot.studytest.test315;

public class Device {
    private String name;
    private Destination destination;
    private DeviceController deviceController;

    public void control(Destination destination) {
        destination.todo();
    }
}