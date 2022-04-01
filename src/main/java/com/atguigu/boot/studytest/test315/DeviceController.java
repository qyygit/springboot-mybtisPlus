package com.atguigu.boot.studytest.test315;

public class DeviceController {
 private Device name;
 private Destination destination;
 public void control(Destination destination){
 destination.todo();
  name.control(destination);
 }
}