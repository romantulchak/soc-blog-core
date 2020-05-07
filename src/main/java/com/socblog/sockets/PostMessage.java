package com.socblog.sockets;


public class PostMessage extends Message {

    private long userId;
    private long currentUserId;

    public PostMessage(String title, long userId) {
        super(title);
        this.userId = userId;
    }
    public PostMessage(String title, long userId, long currentUserId) {
        super(title);
        this.userId = userId;
        this.currentUserId = currentUserId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(long currentUserId) {
        this.currentUserId = currentUserId;
    }
}
