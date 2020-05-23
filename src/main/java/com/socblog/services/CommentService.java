package com.socblog.services;

import com.socblog.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getCommentsForPost(Long postId);
}
