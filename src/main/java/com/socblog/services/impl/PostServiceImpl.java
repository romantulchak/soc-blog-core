package com.socblog.services.impl;

import com.google.gson.Gson;
import com.socblog.dto.PostByDateDTO;
import com.socblog.dto.PostDTO;
import com.socblog.dto.PostPageableDTO;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.repo.PostRepo;
import com.socblog.repo.TagRepo;
import com.socblog.repo.UserRepo;
import com.socblog.services.PostService;
import com.socblog.sockets.PostMessage;
import com.socblog.utils.FileSaver;
import com.socblog.utils.UserLevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Value("${upload.path.post}")
    private String path;

    private PostRepo postRepo;
    private TagRepo tagRepo;
    private UserRepo userRepo;
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public PostServiceImpl(PostRepo postRepo,
                           TagRepo tagRepo,
                           SimpMessagingTemplate simpMessagingTemplate,
                           UserRepo userRepo){
        this.postRepo = postRepo;
        this.tagRepo = tagRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userRepo = userRepo;
    }


    @Override
    public PostPageableDTO getAllPost(User user, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Post> posts = postRepo.findAllBySubscriptions(user.getSubscriptions(), pageable);
        return new PostPageableDTO(posts.toList(), pageable.getPageNumber(), posts.getTotalPages());
    }

    @Override
    public PostPageableDTO getAllForUser(User user, int page) {
        Pageable pageable = PageRequest.of(page, 2 );
        Page<Post> posts = postRepo.findAllForUser(user, pageable);
        return new PostPageableDTO(posts.toList(), pageable.getPageNumber(), posts.getTotalPages());
    }

    @Override
    public ResponseEntity<?> createPost(String postString, MultipartFile file) throws IOException {
        Gson g = new Gson();
        PostDTO postDTO = g.fromJson(postString, PostDTO.class);
        Post post = new Post(postDTO, FileSaver.saveFile(file, path,"posts_images"));

        postRepo.save(post);
        User user = userRepo.findById(post.getUser().getId()).orElse(null);
        assert user != null : "user is null";
        userRepo.save(UserLevelUp.levelUpByPost(user, post));
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


    @Override
    public PostPageableDTO getPostsByTag(String tagName, int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Post> posts = postRepo.findPostsByTagName(tagName, pageable);
        return new PostPageableDTO(posts.toList(), pageable.getPageNumber(), posts.getTotalPages());
    }

    @Override
    public List<PostByDateDTO> getPostsForChart(User user) {
        if(user != null){
            return postRepo.postsByDate(user);
        }
        return null;
    }
}
