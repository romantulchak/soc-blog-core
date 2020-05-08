package com.socblog.dto;

import com.socblog.models.Notification;
import com.socblog.models.User;

import java.util.List;

public class NotificationDTO {

    private Long id;
    private List<Notification> notifications;
    private int notificationCounter;
    private User user;


    public NotificationDTO(){

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public int getNotificationCounter() {
        return notificationCounter;
    }

    public void setNotificationCounter(int notificationCounter) {
        this.notificationCounter = notificationCounter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
