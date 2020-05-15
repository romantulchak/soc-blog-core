package com.socblog.services.impl;

import com.socblog.dto.TagDTO;
import com.socblog.models.Post;
import com.socblog.models.Tag;
import com.socblog.repo.TagRepo;
import com.socblog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private TagRepo tagRepo;

    @Autowired
    public TagServiceImpl(TagRepo tagRepo){
        this.tagRepo = tagRepo;
    }

    @Override
    public List<TagDTO> getAllTags() {
        return tagRepo.tags();
    }

    @Override
    public ResponseEntity<?> createTag(Tag tag) {

        if(tag.getName() != null && !tagRepo.existsByName(tag.getName())){
            tagRepo.save(tag);
            return new ResponseEntity<>("Tag " + tag.getName() + " was created!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something wrong!", HttpStatus.OK);

    }

    @Override
    public ResponseEntity<?> editTag(Tag tag) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteTag(Tag tag) {
        return null;
    }
}
