package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.socblog.dto.CommentDTO;
import com.socblog.dto.CommentPageableDTO;
import com.socblog.models.Comment;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.models.Views;
import com.socblog.services.impl.CommentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @JsonView(Views.CommentFull.class)
    @PreAuthorize("hasRole('USER')")
    public Comment createComment(@RequestBody Comment comment, @PathVariable("userId") User user, @PathVariable("postId") Post post){
        return commentService.addComment(comment, user, post);
    }

    @MessageMapping("/deleteComments/{commentId}/{postId}")
    @SendTo("/topic/deleteComments")
    public ResponseEntity<Object> deletedComment(@DestinationVariable("commentId")Long commentId,@DestinationVariable("postId") Long postId){
        Map<String, Long> map = new HashMap<>();
        map.put("commentId", commentId);
        map.put("postId", postId);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @MessageMapping("/updateComments")
    @SendTo("/topic/updateComments")
    @JsonView(Views.CommentFull.class)
    public Comment updateComments(Long commentId){
        return commentService.updateComments(commentId);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    @PreAuthorize("hasRole('USER') and @userSecurity.hasUserId(authentication, #comment.user.id)")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Comment comment){
        return commentService.deleteComment(comment);
    }

}
