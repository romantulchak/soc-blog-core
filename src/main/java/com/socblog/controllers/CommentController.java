package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.dto.CommentDTO;
import com.socblog.dto.CommentPageableDTO;
import com.socblog.models.Views;
import com.socblog.services.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/comments")
public class CommentController {

    private CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService){
        this.commentService = commentService;
    }

    @GetMapping("/commentsByPost/{id}")
    @JsonView(Views.CommentFull.class)
    public CommentPageableDTO commentsByPost(@PathVariable("id") Long postId, @RequestParam(value = "page") int page){

        return commentService.getCommentsForPost(postId, page);

    }

}
