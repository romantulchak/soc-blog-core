package com.socblog.services;

import com.socblog.dto.PostDTO;
import com.socblog.dto.TagDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.User;

import java.security.Principal;
import java.util.List;

public interface SearchService {

    List<UserDTO> searchUsersByUsername(String username,String currentUsername);
    List<TagDTO> searchTags(String tag);
    List<PostDTO> searchPosts(String name, String currentUser);
}
