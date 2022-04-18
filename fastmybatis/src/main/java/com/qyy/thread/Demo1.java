package com.qyy.thread;

public class Demo1 {

    public static void main(String[] args) {
        TicketOffice ticketOffice = new TicketOffice(new Object(), 20);
        new Thread(ticketOffice, "窗口1").start();
        new Thread(ticketOffice, "窗口2").start();
        new Thread(ticketOffice, "窗口3").start();
    }
}