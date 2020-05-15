package com.socblog.services;

import com.socblog.dto.TagDTO;
import com.socblog.models.Post;
import com.socblog.models.Tag;
import com.socblog.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TagService {

    List<TagDTO> getAllTags();
    ResponseEntity<?> createTag(Tag tag);
    ResponseEntity<?> editTag(Tag tag);
    ResponseEntity<?> deleteTag(Tag tag);

}
