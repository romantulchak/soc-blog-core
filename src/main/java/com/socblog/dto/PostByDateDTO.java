package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Post;
import com.socblog.models.Views;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PostByDateDTO {

    @JsonView(Views.PostFull.class)
    private LocalDate createdDate;

    @JsonView(Views.PostFull.class)
    private Long numberOfCreatedPost;

    public PostByDateDTO(){

    }

    public PostByDateDTO(LocalDate createdDate, Long numberOfCreatedPost) {
        this.createdDate = createdDate;
        this.numberOfCreatedPost = numberOfCreatedPost;


    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getNumberOfCreatedPost() {
        return numberOfCreatedPost;
    }

    public void setNumberOfCreatedPost(Long numberOfCreatedPost) {
        this.numberOfCreatedPost = numberOfCreatedPost;
    }


}
