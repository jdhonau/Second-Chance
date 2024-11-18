package com.techelevator.model;

public class Volunteer {

    private int volnumber;
    private int user_id;
    private String app_status;

    public Volunteer() {
        this.volnumber = 0;
        this.user_id = 0;
        this.app_status = "pending";
    }

    public int getVolnumber() {
        return volnumber;
    }

    public void setVolnumber(int volnumber) {
        this.volnumber = volnumber;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getApp_status() {
        return app_status;
    }

    public void setApp_status(String app_status) {
        this.app_status = app_status;
    }


    public void setUserId(int userId) {
    }

    public void setAppStatus(String appStatus) {
    }

}
