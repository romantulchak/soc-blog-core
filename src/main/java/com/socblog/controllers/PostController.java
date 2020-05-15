package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.socblog.dto.PostDTO;
import com.socblog.dto.PostPageableDTO;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.Views;
import com.socblog.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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


    @GetMapping("/myPosts/{userId}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.PostFull.class)
    public PostPageableDTO myPosts(@PathVariable("userId")User user, @RequestParam(value = "page", defaultValue = "0") int page){
        System.out.println(user.getId());
        return postService.getAllForUser(user, page);
    }

    @GetMapping("/news/{userId}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.PostFull.class)
    public PostPageableDTO news (@PathVariable("userId") User user, @RequestParam(value = "page",defaultValue = "0") int page){
        return postService.getAllPost(user, page);
    }

    @GetMapping("/postsByTag/{tagName}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.PostFull.class)
    public PostPageableDTO postsByTag(@PathVariable("tagName") String tagName, @RequestParam(value = "page", defaultValue = "0") int page){
        return postService.getPostsByTag(tagName, page);

    }
}
