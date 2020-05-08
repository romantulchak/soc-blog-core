package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.Set;

@Entity
public class NotificationBox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.UserFull.class)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "notificationBox")
    @JsonView(Views.UserFull.class)
    private Set<Notification> notifications = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }
}
