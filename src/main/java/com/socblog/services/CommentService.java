package com.socblog.services;

import com.socblog.dto.CommentDTO;
import com.socblog.dto.CommentPageableDTO;
import com.socblog.models.Comment;
import com.socblog.models.Post;
import com.socblog.models.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    CommentPageableDTO getCommentsForPost(Long postId, int page);
    Comment addComment(Comment comment, User user, Post post);
    Comment updateComments(Long commentId);
    ResponseEntity<?> deleteComment(Comment comment);
}
