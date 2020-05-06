package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @JsonView(Views.UserFull.class)
    private Long id;

    @JsonView(Views.UserFull.class)
    private String name;

    @JsonView(Views.UserFull.class)
    @Size(max = 100000)
    private String text;

    @ManyToMany
    @JoinTable(name = "posts_tags",
                joinColumns = @JoinColumn(name = "post_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id")
    )

    @JsonView(Views.UserFull.class)
    private List<Tag> tags = new ArrayList<>();

   /* @ElementCollection
    private List<Image> images = new ArrayList<>();
*/
    @ManyToOne
    private User user;

    public Post(){

    }

    public Post(String name, @Size(max = 100000) String text,User user, List<Tag> tags) {
        this.name = name;
        this.text = text;
        this.user = user;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
/*
    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
*/
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
