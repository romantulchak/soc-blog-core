package com.socblog.services.impl;

import com.socblog.dto.CommentPageableDTO;
import com.socblog.models.Comment;
import com.socblog.models.Post;
import com.socblog.models.User;
import com.socblog.repo.CommentRepo;
import com.socblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;
    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo, SimpMessagingTemplate simpMessagingTemplate){
        this.commentRepo = commentRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public CommentPageableDTO getCommentsForPost(Long postId, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Comment> comments = commentRepo.commentForPost(postId, pageable);
        return new CommentPageableDTO(comments.toList(), pageable.getPageNumber(), comments.getTotalPages(), comments.getTotalElements());
    }

    @Override
    public Comment addComment(Comment comment, User user, Post post) {
        if(comment != null && post != null && user != null){
            Comment c = new Comment(comment,user, post);
            commentRepo.save(c);
            return c;
        }
        return null;
    }

    @Override
    public Comment updateComments(Long commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    @Override
    public ResponseEntity<?> deleteComment(Comment comment) {
        if(comment != null) {
            commentRepo.delete(comment);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something wrong", HttpStatus.OK);
    }
}
