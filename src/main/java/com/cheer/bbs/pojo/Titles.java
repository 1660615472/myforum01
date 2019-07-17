package com.cheer.bbs.pojo;

import lombok.Data;
/*
* 帖子bean
* */
@Data
public class Titles {
    private int id;
    private String cname;
    private String title;
    private String message;
    private String date;
    private Integer comment;

    public Titles() {
    }

    public Titles(String cname, String title, String message,String date) {
        this.cname = cname;
        this.title = title;
        this.message = message;
        this.date = date;
    }


}
