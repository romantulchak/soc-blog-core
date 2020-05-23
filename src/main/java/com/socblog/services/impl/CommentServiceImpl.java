package com.socblog.services.impl;

import com.socblog.dto.CommentDTO;
import com.socblog.repo.CommentRepo;
import com.socblog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CommentDTO> getCommentsForPost(Long postId) {
        return this.commentRepo.commentForPost(postId);
    }
}
