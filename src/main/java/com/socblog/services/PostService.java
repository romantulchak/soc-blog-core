package com.socblog.services;

import com.socblog.dto.PostDTO;
import com.socblog.models.Post;
import com.socblog.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    List<Post> getAllPost();
    List<Post> getAllForUser(User user);
    ResponseEntity<?> createPost(PostDTO post);
    ResponseEntity<?> editPost(Post post);
    ResponseEntity<?> deletePost(Post post);

}
