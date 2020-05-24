package com.socblog.repo;

import com.socblog.dto.CommentDTO;
import com.socblog.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c where c.post.id = :postId order by c.id DESC ")
    Page<Comment> commentForPost(@Param("postId") Long postId, Pageable pageable);
}
