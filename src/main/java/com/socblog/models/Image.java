package com.socblog.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Embeddable;

@Embeddable
public class Image {

    @JsonView(Views.UserFull.class)
    private String imagePath;

    public Image(){

    }

    public Image(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
