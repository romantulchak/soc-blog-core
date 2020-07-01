package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.enums.ENotification;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.NotificationFull.class)
    private Long id;

    @JsonView(Views.NotificationFull.class)
    private String message;

    @JsonView(Views.NotificationFull.class)
    private Boolean isRead;

    @ManyToOne
    private NotificationBox notificationBox;

    @ManyToOne
    @JsonView(Views.NotificationFull.class)
    private User user;

    @JsonView(Views.NotificationFull.class)
    @Enumerated(EnumType.STRING)
    private ENotification eNotification;
    @JsonView(Views.NotificationFull.class)
    private LocalDateTime dateTime;

    @ManyToOne
    @JsonView(Views.NotificationFull.class)
    private Post post;

    public Notification() {
    }

    public Notification(String message, NotificationBox notificationBox, User user, ENotification eNotification) {
        this.message = message;
        this.isRead = false;
        this.notificationBox = notificationBox;
        this.user = user;
        this.eNotification = eNotification;
        this.dateTime = LocalDateTime.now();
    }
    public Notification(String message, NotificationBox notificationBox, User user, ENotification eNotification, Post post) {
        this.message = message;
        this.isRead = false;
        this.notificationBox = notificationBox;
        this.user = user;
        this.eNotification = eNotification;
        this.dateTime = LocalDateTime.now();
        this.post = post;
    }

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

    public void seteNotification(ENotification eNotification) {
        this.eNotification = eNotification;
    }

    public ENotification geteNotification() {
        return eNotification;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
