package com.socblog.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Post;
import com.socblog.models.Views;

import java.util.List;

public class PostPageableDTO {
    @JsonView(Views.PostFull.class)
    private List<Post> posts;

    @JsonView(Views.PostFull.class)
    private int currentPage;

    @JsonView(Views.PostFull.class)
    private int totalPages;

    public PostPageableDTO(List<Post> posts, int currentPage, int totalPages) {
        this.posts = posts;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public PostPageableDTO() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
}
