package com.example.micromap.domain;

public class Member {
    //아이디
    private String user_id;

    //비밀번호
    private String password;

    //나의 위치
//    private double latitude;
//    private double longitude;

    //내가 찜한 가게 list
    //약속 장소 list
    //주문 기록 list - 과거, 현재


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}


