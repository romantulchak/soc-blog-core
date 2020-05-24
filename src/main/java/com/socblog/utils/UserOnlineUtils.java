package com.socblog.utils;

public class UserOnlineUtils {
    private Long userId;
    private boolean isOnline;

    public UserOnlineUtils(){

    }


    public UserOnlineUtils(Long userId, boolean isOnline) {
        this.userId = userId;
        this.isOnline = isOnline;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
