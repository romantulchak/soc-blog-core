package com.socblog.services;

import com.socblog.dto.CommentDTO;
import com.socblog.dto.CommentPageableDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    CommentPageableDTO getCommentsForPost(Long postId, int page);
}
