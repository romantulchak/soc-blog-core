package com.socblog.services;

import com.socblog.dto.PostDTO;
import com.socblog.dto.TagDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.Post;
import com.socblog.models.Tag;
import com.socblog.models.User;

public interface ConvertToDTO {
    default UserDTO convertToDto(User user, User currentUser){
        return new UserDTO(user,currentUser);
    }
    default TagDTO convertTagToDto(Tag tag){
        return new TagDTO(tag);
    }

    default PostDTO postDTOS(User currentUser, Post post){
        return new PostDTO(post, currentUser);
    }

}
