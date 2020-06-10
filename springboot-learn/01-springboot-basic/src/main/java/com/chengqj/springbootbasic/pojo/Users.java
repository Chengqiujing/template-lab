package com.chengqj.springbootbasic.pojo;

public class Users {
    private Integer userid;
    private String userName;
    private Integer userAge;

    public Users(Integer userid, String userName, Integer userAge) {
        this.userid = userid;
        this.userName = userName;
        this.userAge = userAge;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
}
