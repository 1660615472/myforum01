package com.cheer.bbs.pojo;
/*
* 网站运营状况bean
* */




public class WebInfo {
    private int userNum;//注册会员人数
    private int titleNum;//论坛帖子数量
    private int messageNum;//回复数

    public WebInfo(){

    }

    public WebInfo(int userNum,int titleNum,int messageNum){
        this.userNum=userNum;
        this.titleNum=titleNum;
        this.messageNum=messageNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getTitleNum() {
        return titleNum;
    }

    public void setTitleNum(int titleNum) {
        this.titleNum = titleNum;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    @Override
    public String toString() {
        return "WebInfo{" +
                "userNum=" + userNum +
                ", titleNum=" + titleNum +
                ", messageNum=" + messageNum +
                '}';
    }
}
