package com.socblog.services.impl;

import com.socblog.dto.PostDTO;
import com.socblog.dto.TagDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.Post;
import com.socblog.models.Tag;
import com.socblog.models.User;
import com.socblog.repo.PostRepo;
import com.socblog.repo.TagRepo;
import com.socblog.repo.UserRepo;
import com.socblog.services.ConvertToDTO;
import com.socblog.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService, ConvertToDTO {

    private final UserRepo userRepo;
    private final TagRepo tagRepo;
    private final PostRepo postRepo;

    @Autowired
    public SearchServiceImpl(UserRepo userRepo, TagRepo tagRepo, PostRepo postRepo){
        this.userRepo = userRepo;
        this.tagRepo = tagRepo;
        this.postRepo = postRepo;
    }

    @Override
    public List<UserDTO> searchUsersByUsername(String username, String currentUsername) {
        User user = userRepo.findByUsername(currentUsername).orElse(null);
        List<User> users = userRepo.findAllByUsernameContaining(username);
        return users.stream().map(x->convertToDto(x, user)).limit(25).collect(Collectors.toList());
    }

    @Override
    public List<TagDTO> searchTags(String tag) {
        List<Tag> tags = tagRepo.findAllByNameContaining(tag);
        return tags.stream().map(this::convertTagToDto).limit(25).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPosts(String name, String currentUsername) {
        User user = userRepo.findByUsername(currentUsername).orElse(null);
        List<Post> posts = postRepo.findAllByNameContaining(name);
        return posts.stream().map(x->postDTOS(user, x)).limit(25).collect(Collectors.toList());
    }
}
