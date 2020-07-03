package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.dto.PostDTO;
import com.socblog.dto.TagDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.Views;
import com.socblog.services.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(value = "*", maxAge = 3600)
public class SearchController {

    private SearchServiceImpl searchService;

    @Autowired
    private SearchController(SearchServiceImpl searchService){
        this.searchService = searchService;
    }

    @GetMapping("/searchPeople")
    @JsonView(Views.UserFull.class)
    public List<UserDTO> findUsersByUsername(@RequestParam(value = "username") String username, Principal principal){
        return searchService.searchUsersByUsername(username, principal.getName());
    }

    @GetMapping("/searchTags")
    @JsonView(Views.TagFull.class)
    public List<TagDTO> findTags(@RequestParam(value = "tag") String tag){
        return searchService.searchTags(tag);
    }

    @GetMapping("/searchPosts")
    @JsonView(Views.PostFull.class)
    public List<PostDTO> findPosts(@RequestParam(value = "name") String name, Principal principal){
        return searchService.searchPosts(name, principal.getName());
    }


}
