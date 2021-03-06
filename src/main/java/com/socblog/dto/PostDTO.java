package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Post;
import com.socblog.models.Tag;
import com.socblog.models.User;
import com.socblog.models.Views;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class PostDTO {
    @JsonView(Views.PostFull.class)
    private long id;

    @JsonView(Views.PostFull.class)
    private String name;

    @JsonView(Views.PostFull.class)
    private String text;

    @JsonView(Views.PostFull.class)
    private List<Tag> tags;
    @JsonView(Views.PostFull.class)
    private User user;

    @JsonView(Views.PostFull.class)
    private String image;

    @JsonView(Views.PostFull.class)
    private LocalDate createdDate;
    @JsonView(Views.PostFull.class)
    private String smallDescription;

    @JsonView(Views.PostFull.class)
    private Set<User> likes;

    @JsonView(Views.PostFull.class)
    private boolean meLiked;

    @JsonView(Views.PostFull.class)
    private int likesCounter;

    @JsonView(Views.PostFull.class)
    private int commentsCounter;

    public PostDTO() {
    }

    public PostDTO(Post post, User currentUser) {
        this.id = post.getId();
        this.name = post.getName();
        this.text = post.getText();
        this.tags = post.getTags();
        this.user = post.getUser();
        this.image = post.getImagePath();
        this.createdDate = LocalDate.now();
        this.smallDescription = post.getSmallDescription();
        this.likes = post.getLikes();
        this.likesCounter = post.getLikes().size();
        this.meLiked = this.likes.contains(currentUser);

        this.commentsCounter = post.getComments().size();
    }

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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public void setSmallDescription(String smallDescription) {
        this.smallDescription = smallDescription;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public boolean isMeLiked() {
        return meLiked;
    }


    public void setMeLiked(boolean meLiked) {
        this.meLiked = meLiked;
    }

    public int getLikesCounter() {
        return likesCounter;
    }

    public void setLikesCounter(int likesCounter) {
        this.likesCounter = likesCounter;
    }

    public int getCommentsCounter() {
        return commentsCounter;
    }

    public void setCommentsCounter(int commentsCounter) {
        this.commentsCounter = commentsCounter;
    }
}
