package com.example.micromap.domain;

public class Member {
    //아이디
    private String userId;

    //비밀번호
    private String password;

    //나의 위치
//    private double latitude;
//    private double longitude;

    //내가 찜한 가게 list
    //약속 장소 list
    //주문 기록 list - 과거, 현재


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


