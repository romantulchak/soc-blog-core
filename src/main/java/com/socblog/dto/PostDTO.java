package com.socblog.dto;

import com.socblog.models.User;

import java.util.List;

public class PostDTO {
    private String name;
    private String text;
    private List<Long> tags;
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Long> getTags() {
        return tags;
    }

    public void setTags(List<Long> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostDTO{" +
                "name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", tags=" + tags +
                '}';
    }
}
