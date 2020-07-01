package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Notification;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.Views;
import com.socblog.models.enums.ENotification;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

public class NotificationDTO {


    @JsonView(Views.NotificationFull.class)
    private Long id;

    @JsonView(Views.NotificationFull.class)
    private UserDTO user;

    @JsonView(Views.NotificationFull.class)
    private String message;

    @JsonView(Views.NotificationFull.class)
    private Boolean isRead;

    @JsonView(Views.NotificationFull.class)
    @Enumerated(EnumType.STRING)
    private ENotification eNotification;

    @JsonView(Views.NotificationFull.class)
    private LocalDateTime dateTime;

    @JsonView(Views.NotificationFull.class)
    private Post post;

    public  NotificationDTO(){

    }

    public NotificationDTO(Notification notification, UserDTO user) {
        this.id = notification.getId();
        this.user = user;
        this.message = notification.getMessage();
        this.isRead = notification.getRead();
        this.eNotification = notification.geteNotification();
        this.dateTime = notification.getDateTime();
        this.post = notification.getPost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public ENotification geteNotification() {
        return eNotification;
    }

    public void seteNotification(ENotification eNotification) {
        this.eNotification = eNotification;
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
