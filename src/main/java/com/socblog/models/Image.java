package com.socblog.models;

import javax.persistence.Embeddable;

@Embeddable
public class Image {

    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
