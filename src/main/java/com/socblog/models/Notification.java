package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.UserFull.class)
    private Long id;

    @JsonView(Views.UserFull.class)
    private String message;

    @JsonView(Views.UserFull.class)
    private Boolean isRead;


    @ManyToOne
    private NotificationBox notificationBox;

    @ManyToOne
    @JsonView(Views.UserFull.class)
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationBox getNotificationBox() {
        return notificationBox;
    }

    public void setNotificationBox(NotificationBox notificationBox) {
        this.notificationBox = notificationBox;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
