package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.CommentFull.class)
    private Long id;

    @Size(max = 5000)
    @JsonView(Views.CommentFull.class)
    private String text;

    @ManyToOne
    @JsonView(Views.CommentFull.class)
    private Post post;

    @ManyToOne
    @JsonView(Views.CommentFull.class)
    private User user;

    @OneToMany(mappedBy = "comment")
    @JsonView(Views.CommentFull.class)
    private List<Replay> replays;

    @JsonView(Views.CommentFull.class)
    private LocalDateTime createdDate;

    public Comment() {
    }
    public Comment(Comment comment, User user,Post post){
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = user;
        this.post = post;
        this.replays = new ArrayList<>();
        this.createdDate = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Replay> getReplays() {
        return replays;
    }

    public void setReplays(List<Replay> replays) {
        this.replays = replays;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
