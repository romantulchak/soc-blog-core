package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.dto.PostDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.UserFull.class, Views.PostFull.class, Views.CommentFull.class})
    private Long id;

    @JsonView({Views.UserFull.class,Views.PostFull.class})
    private String name;

    @JsonView({Views.UserFull.class,Views.PostFull.class})
    private String text;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "posts_tags",
                joinColumns = @JoinColumn(name = "post_id"),
                inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonView({Views.UserFull.class, Views.PostFull.class})
    private List<Tag> tags = new ArrayList<>();

    @JsonView({Views.UserFull.class, Views.PostFull.class})
    private String imagePath;


    @ManyToOne
    @JsonView(Views.PostFull.class)
    private User user;

    @JsonView(Views.PostFull.class)
    private LocalDate createdDate;

    @JsonView(Views.PostFull.class)
    @Size(max = 1000)
    private String smallDescription;


    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_likes",
                joinColumns = @JoinColumn(name = "post_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonView(Views.PostFull.class)
    private Set<User> likes = new HashSet<>();

    public Post(){

    }

    public Post(PostDTO postDTO, String imagePath) {
        this.name = postDTO.getName();
        this.text = postDTO.getText();
        this.user = postDTO.getUser();
        this.tags = postDTO.getTags();
        this.imagePath = imagePath;
        this.smallDescription =  postDTO.getSmallDescription();
        this.createdDate = LocalDate.now();

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

}
