package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.Views;

import java.time.LocalDate;
import java.util.List;

public class UserDTO {

    @JsonView(Views.UserFull.class)
    private Long id;

    @JsonView(Views.UserFull.class)
    private String username;

    @JsonView(Views.UserFull.class)
    private String email;

    @JsonView(Views.UserFull.class)
    private Boolean isNew;

    @JsonView(Views.UserFull.class)
    private String firstName;

    @JsonView(Views.UserFull.class)
    private String lastName;

    @JsonView(Views.UserFull.class)
    private String city;

    @JsonView(Views.UserFull.class)
    private LocalDate birthDay;

    @JsonView(Views.UserFull.class)
    private String avatar;

    @JsonView(Views.UserFull.class)
    private String country;

    @JsonView(Views.UserFull.class)
    private String placeOfWork;

    @JsonView(Views.UserFull.class)
    private String gender;

    @JsonView(Views.UserFull.class)
    private List<Post> posts;

    public UserDTO(){

    }

    public UserDTO(Long id, String username, String email, Boolean isNew, String firstName, String lastName, String city, LocalDate birthDay, String avatar, String country, String placeOfWork, String gender, List<Post> posts) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isNew = isNew;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.birthDay = birthDay;
        this.avatar = avatar;
        this.country = country;
        this.placeOfWork = placeOfWork;
        this.gender = gender;
        this.posts = posts;

    }

    public UserDTO(User user){
        this.id = user.getId();
        this.username =user.getUsername();
        this.email = user.getEmail();
        this.isNew = user.getNew();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.city = user.getCity();
        this.birthDay = user.getBirthDay();
        this.avatar = user.getAvatar();
        this.country = user.getCountry();
        this.placeOfWork = user.getPlaceOfWork();
        this.gender = user.getGender();
        this.posts = user.getPosts();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
