package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Image;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.Views;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class UserDTO {

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private Long id;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String username;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String email;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private Boolean isNew;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String firstName;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String lastName;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String city;

    @JsonView(Views.UserFull.class)
    private LocalDate birthDay;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String avatar;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String country;

    @JsonView(Views.UserFull.class)
    private String placeOfWork;

    @JsonView(Views.UserFull.class)
    private String gender;

    @JsonView(Views.UserFull.class)
    private List<Post> posts;

    private Set<User> subscribers;

    private Set<User> subscriptions;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private long subscribersCounter;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private long subscriptionsCounter;
    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private long postsCounter;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private Boolean isSubscribe;

    @JsonView(Views.UserFull.class)
    private List<Image> images;

    public UserDTO(){

    }

    public UserDTO(User user ){
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
        this.subscribers = user.getSubscribers();
        this.subscriptions = user.getSubscriptions();
        this.subscribersCounter = user.getSubscribers().size();
        this.subscriptionsCounter = user.getSubscriptions().size();
        this.isSubscribe = this.subscriptions.contains(user);
        this.postsCounter = user.getPosts().size();
        this.images = user.getImages();
    }


    public UserDTO(User user, User userInSystem){

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
        this.subscribers = user.getSubscribers();
        this.subscriptions = user.getSubscriptions();
        this.isSubscribe = userInSystem.getSubscriptions().contains(user);
        this.subscribersCounter = user.getSubscribers().size();
        this.subscriptionsCounter = user.getSubscriptions().size();
        this.postsCounter = user.getPosts().size();
        this.images = user.getImages();
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

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Boolean getSubscribe() {
        return isSubscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        isSubscribe = subscribe;
    }

    public long getSubscribersCounter() {
        return subscribersCounter;
    }

    public void setSubscribersCounter(long subscribersCounter) {
        this.subscribersCounter = subscribersCounter;
    }

    public long getSubscriptionsCounter() {
        return subscriptionsCounter;
    }

    public void setSubscriptionsCounter(long subscriptionsCounter) {
        this.subscriptionsCounter = subscriptionsCounter;
    }

    public long getPostsCounter() {
        return postsCounter;
    }

    public void setPostsCounter(long postsCounter) {
        this.postsCounter = postsCounter;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
