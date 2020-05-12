package com.socblog.services.impl;

import com.google.gson.Gson;
import com.socblog.dto.PostDTO;
import com.socblog.models.Post;
import com.socblog.models.Tag;
import com.socblog.models.User;
import com.socblog.repo.PostRepo;
import com.socblog.repo.TagRepo;
import com.socblog.services.PostService;
import com.socblog.sockets.PostMessage;
import com.socblog.sockets.ResponseMessage;
import com.socblog.utils.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Value("${upload.path.post}")
    private String path;

    private PostRepo postRepo;
    private TagRepo tagRepo;
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public PostServiceImpl(PostRepo postRepo, TagRepo tagRepo, SimpMessagingTemplate simpMessagingTemplate){
        this.postRepo = postRepo;
        this.tagRepo = tagRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public List<Post> getAllForUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> createPost(String postString, MultipartFile file) throws IOException {
        Gson g = new Gson();
        PostDTO postDTO = g.fromJson(postString, PostDTO.class);
        Post post = new Post(postDTO, FileSaver.saveFile(file, path,"posts_images"));
        postRepo.save(post);
        simpMessagingTemplate.convertAndSend("/topic/update", new PostMessage("updatePosts", postDTO.getUser().getId()));
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> editPost(Post post) {
        return null;
    }

    @Override
    public ResponseEntity<?> deletePost(Post post) {
        return null;
    }



}
