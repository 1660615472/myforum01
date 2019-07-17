package com.cheer.bbs.pojo;


import lombok.Data;
/*
* 用户bean
* */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String hiredate;
    private String avatar;

    public User() {
    }

    public User( String name, String password, String hiredate, String avatar) {
        this.name = name;
        this.password = password;
        this.hiredate = hiredate;
        this.avatar = avatar;
    }


}
