package com.socblog.controllers;

import com.google.gson.Gson;
import com.socblog.dto.PostDTO;
import com.socblog.models.Post;
import com.socblog.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/posts")
public class PostController {

    private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }

    @PostMapping(value = "/createPost")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPost(@RequestPart(value = "image", required = false) MultipartFile image, @RequestPart("postDTO") String postString) throws IOException {
        return postService.createPost(postString, image);
    }



}
