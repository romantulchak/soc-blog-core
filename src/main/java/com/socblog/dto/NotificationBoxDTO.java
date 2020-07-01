package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Notification;
import com.socblog.models.NotificationBox;
import com.socblog.models.User;
import com.socblog.models.Views;

import javax.xml.bind.Element;
import java.util.*;
import java.util.stream.Collectors;

public class NotificationBoxDTO {

    @JsonView(Views.NotificationFull.class)
    private Long id;

    private List<Notification> notifications;
    @JsonView(Views.NotificationFull.class)
    private List<NotificationDTO> notificationDTOS;
    @JsonView(Views.NotificationFull.class)
    private Long notificationCounter;


    public NotificationBoxDTO() {
    }

    public NotificationBoxDTO(NotificationBox notificationBox) {
        this.id = notificationBox.getId();
        this.notifications = notificationBox.getNotifications().stream().limit(100).collect(Collectors.toList());

    }

    public NotificationBoxDTO(NotificationBoxDTO notificationBoxDTO, List<NotificationDTO> notificationDTO){
        this.id = notificationBoxDTO.getId();
        this.notificationDTOS = notificationDTO.stream().limit(100).collect(Collectors.toList());

        this.notificationCounter = notificationDTOS.stream().filter(x->!x.getRead()).count();
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

    public Long getNotificationCounter() {
        return notificationCounter;
    }

    public void setNotificationCounter(Long notificationCounter) {
        this.notificationCounter = notificationCounter;
    }

    public List<NotificationDTO> getNotificationDTOS() {
        return notificationDTOS;
    }

    public void setNotificationDTOS(List<NotificationDTO> notificationDTOS) {
        this.notificationDTOS = notificationDTOS;
    }
}
