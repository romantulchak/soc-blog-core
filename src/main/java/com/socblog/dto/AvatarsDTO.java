package com.socblog.dto;

import org.springframework.web.multipart.MultipartFile;

public class AvatarsDTO {

    private String avatar;
    private MultipartFile file;

    public AvatarsDTO() {
    }

    public AvatarsDTO(String avatar, MultipartFile file) {
        this.avatar = avatar;
        this.file = file;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

