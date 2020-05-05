package com.socblog.dto;

import java.time.LocalDate;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Boolean isNew;
    private String firstName;
    private String lastName;
    private String city;
    private LocalDate birthDay;
    private String avatar;
    private String country;
    private String placeOfWork;
    private String gender;
    public UserDTO(){

    }

    public UserDTO(Long id, String username, String email, Boolean isNew, String firstName, String lastName, String city, LocalDate birthDay, String avatar, String country, String placeOfWork, String gender) {
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
}
