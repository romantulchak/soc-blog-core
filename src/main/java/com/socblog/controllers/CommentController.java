package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.dto.CommentDTO;
import com.socblog.dto.CommentPageableDTO;
import com.socblog.models.Comment;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.Views;
import com.socblog.services.impl.CommentServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PostMapping("/createComment/{userId}/{postId}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.CommentFull.class)
    public Comment createComment(@RequestBody Comment comment, @PathVariable("userId")User user, @PathVariable("postId")Post post){
        return commentService.addComment(comment, user, post);
    }

}
