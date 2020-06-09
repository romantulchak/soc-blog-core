package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({Views.UserFull.class, Views.PostFull.class, Views.TagFull.class})
    private Long id;

    @JsonView({Views.UserFull.class,Views.PostFull.class,Views.TagFull.class})
    private String name;


    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private List<Post> posts;

    public Tag(){

    }

    public Tag(Long id, String name, List<Post> posts, User user) {
        this.id = id;
        this.name = name;
        this.posts = posts;
        this.user = user;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id.equals(tag.id) &&
                name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
