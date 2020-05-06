package com.socblog.services.impl;

import com.socblog.dto.PostDTO;
import com.socblog.models.Post;
import com.socblog.models.Tag;
import com.socblog.models.User;
import com.socblog.repo.PostRepo;
import com.socblog.repo.TagRepo;
import com.socblog.services.PostService;
import com.socblog.sockets.PostMessage;
import com.socblog.sockets.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

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
    public ResponseEntity<?> createPost(PostDTO postDTO) {
        List<Tag> tags = new ArrayList<>();
        postDTO.getTags().forEach(el-> {
                    Tag tag = tagRepo.findById(el).orElse(null);
                    tags.add(tag);
            }
        );
        Post post = new Post(postDTO.getName(), postDTO.getText(), postDTO.getUser(), tags);
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
