package com.socblog.services;

import com.socblog.dto.PostDTO;
import com.socblog.models.Post;
import com.socblog.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<Post> getAllPost();
    List<Post> getAllForUser(User user);
    ResponseEntity<?> createPost(String post, MultipartFile file) throws IOException;
    ResponseEntity<?> editPost(Post post);
    ResponseEntity<?> deletePost(Post post);

}
