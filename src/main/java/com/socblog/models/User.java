package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class, Views.PostFull.class})
    private long id;

    @NotBlank
    @Size(max = 25, min = 3)
    @JsonView({Views.UserFull.class, Views.PostFull.class, Views.CommentFull.class})
    private String username;

    @NotBlank
    @Email
    @Size(max = 50)
    @JsonView(Views.UserFull.class)
    private String email;

    @NotBlank
    @Size(max = 120, min = 6)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name ="user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonView({Views.UserFull.class, Views.UserSubscribeFull.class})
    private Set<Role> roles = new HashSet<>();


    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private Boolean isNew = true;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String city;


    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class, Views.PostFull.class})
    @NotBlank
    private String firstName;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class, Views.PostFull.class})
    @NotBlank
    private String lastName;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private LocalDate birthDay;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String country;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class, Views.PostFull.class, Views.CommentFull.class})
    private String avatar;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String placeOfWork;

    @JsonView({Views.UserFull.class,Views.UserSubscribeFull.class})
    private String gender;

    @OneToMany(mappedBy = "user")
    @JsonView(Views.UserFull.class)
    private List<Post> posts;

    @ElementCollection
    @JsonView(Views.UserFull.class)
    private List<Image> images;


    @JsonView(Views.UserFull.class)
    private int accountLvl = 1;


    @JsonView(Views.UserFull.class)
    private double currentExperience = 0;

    @JsonView(Views.UserFull.class)
    private double experienceToNextLevel = 500;


    @JsonView(Views.UserFull.class)
    private boolean isOnline;



    @OneToMany(mappedBy = "user")
    private List<Tag> tags;



    @ManyToMany
    @JoinTable(
            name="user_subscribtion",
            joinColumns = @JoinColumn(name="channel_id"),
            inverseJoinColumns = @JoinColumn(name = "subscriber_id")
    )

    private Set<User> subscribers = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name="user_subscribtion",
            joinColumns = @JoinColumn(name="subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )

    private Set<User> subscriptions = new HashSet<>();


    @OneToOne(mappedBy = "user")
    private NotificationBox notificationBox;


    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;


    @JsonView(Views.UserFull.class)
    private LocalDate createdUser;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    public User() {
    }

    public User(@NotBlank @Size(max = 25, min = 3) String username, @NotBlank @Email @Size(max = 50) String email, @NotBlank @Size(max = 120, min = 6) String password ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdUser = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Set<User> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<User> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<User> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<User> subscribers) {
        this.subscribers = subscribers;
    }

    public NotificationBox getNotificationBox() {
        return notificationBox;
    }

    public void setNotificationBox(NotificationBox notificationBox) {
        this.notificationBox = notificationBox;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getAccountLvl() {
        return accountLvl;
    }

    public void setAccountLvl(int accountLvl) {
        this.accountLvl = accountLvl;
    }

    public double getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(double currentExperience) {
        this.currentExperience = currentExperience;
    }

    public double getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    public void setExperienceToNextLevel(double experienceToNextLevel) {
        this.experienceToNextLevel = experienceToNextLevel;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public LocalDate getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(LocalDate createdUser) {
        this.createdUser = createdUser;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
