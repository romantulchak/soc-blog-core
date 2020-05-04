package com.socblog.dto;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Boolean isNew;

    public UserDTO(){

    }
    public UserDTO(Long id, String username, String email, Boolean isNew) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isNew = isNew;
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
}
