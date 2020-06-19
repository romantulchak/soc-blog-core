package com.socblog.services.impl;

import com.socblog.dto.CommentDTO;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo){
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentPageableDTO getCommentsForPost(Long postId, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Comment> comments = commentRepo.commentForPost(postId, pageable);
        return new CommentPageableDTO(comments.toList(), pageable.getPageNumber(), comments.getTotalPages(), comments.getTotalElements());
    }

    @Override
    public Comment addComment(Comment comment, User user, Post post) {
        if(comment != null && user != null){
            commentRepo.save(new Comment(comment,user, post));
            return comment;
        }
        return null;
    }
}
