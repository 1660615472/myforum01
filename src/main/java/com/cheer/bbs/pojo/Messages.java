package com.cheer.bbs.pojo;

import lombok.Data;
/*
* 评论bean
* */
@Data
public class Messages {
    private int id;
    private int tid;
    private String message;
    private int floor;
    private String cname;
    private String date;

    public Messages() {
    }

    public Messages(int tid, String message, int floor,String cname,String date) {
        this.tid = tid;
        this.message = message;
        this.floor = floor;
        this.cname=cname;
        this.date = date;
    }

}
