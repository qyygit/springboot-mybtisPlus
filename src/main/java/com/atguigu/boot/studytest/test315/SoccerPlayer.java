package com.atguigu.boot.studytest.test315;

public class SoccerPlayer {

    private String name;
    private Soccer soccer;


    public Soccer test(Soccer soccer) {
        soccer.setSoccerName("组合类");
        return soccer;
    }

 public static void main(String[] args) {

  SoccerPlayer soccerPlayer = new SoccerPlayer();
  Soccer soccer = soccerPlayer.test(soccerPlayer.soccer);
  System.out.println(soccer.getSoccerName());
 }
}