package com.socblog.services.impl;

import com.google.gson.Gson;
import com.socblog.dto.PostByDateDTO;
import com.socblog.dto.PostDTO;
import com.socblog.dto.PostPageableDTO;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.repo.PostRepo;
import com.socblog.repo.UserRepo;
import com.socblog.services.PostService;
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
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Value("${upload.path.post}")
    private String path;

    private final PostRepo postRepo;
    private final UserRepo userRepo;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public PostServiceImpl(PostRepo postRepo,
                           UserRepo userRepo,
                           SimpMessagingTemplate simpMessagingTemplate
                           ){
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.simpMessagingTemplate =simpMessagingTemplate;
    }


    @Override
    public PostPageableDTO getAllPost(User user, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Post> posts = postRepo.findAllBySubscriptions(user.getSubscriptions(), pageable);
        List<PostDTO> postDTOS = posts.stream().map(x->postDTOS(user, x)).collect(Collectors.toList());
        return new PostPageableDTO(postDTOS, pageable.getPageNumber(), posts.getTotalPages());
    }

    private PostDTO postDTOS(User currentUser, Post post){
        PostDTO postDTO = new PostDTO(post);
        if(isContains(currentUser,post.getLikes())){
            postDTO.setMeLiked(true);
        }
        return postDTO;
    }

    @Override
    public PostPageableDTO getAllForUser(User user, int page, User currentUser) {
        Pageable pageable = PageRequest.of(page, 6 );
        Page<Post> posts = postRepo.findAllForUser(user, pageable);
        List<PostDTO> postDTOS = posts.stream().map(x->postDTOS(currentUser, x)).collect(Collectors.toList());
        return new PostPageableDTO(postDTOS, pageable.getPageNumber(), posts.getTotalPages());
    }


    private boolean isContains(User user, Set<User> likes){
        return likes.contains(user);
    }

    @Override
    public ResponseEntity<?> createPost(String postString, MultipartFile file) throws IOException {
        Gson g = new Gson();
        PostDTO postDTO = g.fromJson(postString, PostDTO.class);
        Post post = new Post(postDTO, FileSaver.saveFile(file, path,"posts_images"));
        postRepo.save(post);
        User user = userRepo.findById(post.getUser().getId()).orElse(null);
        assert user != null : "user is null";
        userRepo.save(new UserLevelUp().levelUpByPost(user, post));
        simpMessagingTemplate.convertAndSend( "/topic/updatePost", postDTOS(user, post));
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> editPost(Post post) {
        return null;
    }
    @Override
    public ResponseEntity<?> deletePost(Post post, User user) {
        if(post.getUser().equals(user)){
            postRepo.delete(post);
            simpMessagingTemplate.convertAndSend( "/topic/updatePost/delete", user.getId());
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Something wrong", HttpStatus.OK);
        }
    }


    @Override
    public PostPageableDTO getPostsByTag(String tagName, int page, User currentUser) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Post> posts = postRepo.findPostsByTagName(tagName, pageable);
        List<PostDTO> postDTOS = posts.stream().map(x->postDTOS(currentUser, x)).collect(Collectors.toList());
        return new PostPageableDTO(postDTOS, pageable.getPageNumber(), posts.getTotalPages());
    }

    @Override
    public List<PostByDateDTO> getPostsForChart(User user) {
        if(user != null){
            return postRepo.postsByDate(user);
        }
        return null;
    }

    @Override
    public PostDTO getPostsBy(Post post) {
        return new PostDTO(post);
    }

    /**
     * Set/remove like  for post
     * @param postId - current post
     * @param currentUserId - user in system
     * @return - if current user isn't in post likes - add him
     * else remove him
     */
    @Override
    public PostDTO setLike(Long postId, Long currentUserId) {
        User currentUser = userRepo.findById(currentUserId).orElse(null);
        Post post = postRepo.findById(postId).orElse(null);
        if(post != null && currentUser != null){
            if(isContains(currentUser, post.getLikes())){
                post.getLikes().remove(currentUser);
            }else{
               userRepo.save(new UserLevelUp().levelUpByLike(currentUser));
                post.getLikes().add(currentUser);

            }

            postRepo.save(post);
            simpMessagingTemplate.convertAndSend("/topic/myLike/", currentUserId);
        }
        return postDTOS(currentUser, post);
    }
}
