package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.socblog.dto.PostDTO;
import com.socblog.models.Post;
import com.socblog.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/posts")
public class PostController {

    private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }

    @PostMapping("/createPost")
    @PreAuthorize("hasRole('USER')")
    @JsonFormat(shape = JsonFormat.Shape.ARRAY)
    public ResponseEntity<?> createPost(@RequestBody PostDTO post){

        return postService.createPost(post);
    }
}
