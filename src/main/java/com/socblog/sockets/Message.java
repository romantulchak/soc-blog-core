package com.socblog.sockets;

public abstract class Message {

    private String title;

    public Message(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }
}
