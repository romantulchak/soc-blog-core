package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Replay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.CommentFull.class)
    private Long id;

    @ManyToOne
    @JsonView(Views.CommentFull.class)
    private User user;

    @Size(max = 600)
    @JsonView(Views.CommentFull.class)
    private String text;

    @ManyToOne
    private Comment comment;

    @JsonView(Views.CommentFull.class)
    private LocalDateTime createdDate;

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

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
