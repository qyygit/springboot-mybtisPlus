package com.atguigu.boot.thread;

public class Main {
 
	public static void main(String[] args) {
		Thread1 mTh1=new Thread1("A");
		Thread1 mTh2=new Thread1("B");
		new Thread(new Thread1("a")).start();


	}
 
}
