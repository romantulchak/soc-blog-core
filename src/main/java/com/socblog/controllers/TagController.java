package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.dto.TagDTO;
import com.socblog.models.Tag;
import com.socblog.models.User;
import com.socblog.models.Views;
import com.socblog.services.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/tags")
public class TagController {

    private TagServiceImpl tagService;

    @Autowired
    public TagController(TagServiceImpl tagService){
        this.tagService = tagService;
    }

    @GetMapping("/getTags")
    @JsonView(Views.TagFull.class)
    public List<TagDTO> getTags(){
        return tagService.getAllTags();
    }

    @PostMapping("/createTag/{userId}")
    public ResponseEntity<?> createTag(@RequestBody Tag tag, @PathVariable("userId") User user){
        return tagService.createTag(tag, user);
    }


}
