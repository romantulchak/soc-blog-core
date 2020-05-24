package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Comment;
import com.socblog.models.Views;

import java.util.List;

public class CommentPageableDTO {

    @JsonView(Views.CommentFull.class)
    public List<Comment> comments;

    @JsonView(Views.CommentFull.class)
    public int currentPage;

    @JsonView(Views.CommentFull.class)
    public int totalPages;
    @JsonView(Views.CommentFull.class)
    private Long commentsCounter;

    public CommentPageableDTO(){

    }

    public CommentPageableDTO(List<Comment> comments, int currentPage, int totalPages, Long commentsCounter) {
        this.comments = comments;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.commentsCounter = commentsCounter;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Long getCommentsCounter() {
        return commentsCounter;
    }

    public void setCommentsCounter(Long commentsCounter) {
        this.commentsCounter = commentsCounter;
    }
}
