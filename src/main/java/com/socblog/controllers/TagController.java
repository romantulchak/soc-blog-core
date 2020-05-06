package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.models.Tag;
import com.socblog.models.Views;
import com.socblog.services.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @JsonView(Views.UserFull.class)
    public List<Tag> getTags(){
        return this.tagService.getAllTags();
    }


}
