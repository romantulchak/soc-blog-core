package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Comment;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.Views;

public class CommentDTO {

    @JsonView(Views.CommentFull.class)
    private Long id;

    @JsonView(Views.CommentFull.class)
    private String text;

    private Post post;


    @JsonView(Views.CommentFull.class)
    private User user;



    public CommentDTO(){

    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.post = comment.getPost();
        this.text = comment.getText();
        this.user = comment.getUser();
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

}
