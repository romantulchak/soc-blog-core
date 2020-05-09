package com.socblog.sockets;


public class PostMessage extends Message {

    private long userId;
    private long currentUserId;
    private long notificationCounter;
    public PostMessage(String title, long userId) {
        super(title);
        this.userId = userId;
    }
    public PostMessage(String title, long userId, long currentUserId) {
        super(title);
        this.userId = userId;
        this.currentUserId = currentUserId;
    }
    public PostMessage(String title, long userId, long currentUserId, long notificationCounter) {
        super(title);
        this.userId = userId;
        this.currentUserId = currentUserId;
        this.notificationCounter = notificationCounter;
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

    public long getNotificationCounter() {
        return notificationCounter;
    }

    public void setNotificationCounter(long notificationCounter) {
        this.notificationCounter = notificationCounter;
    }
}
