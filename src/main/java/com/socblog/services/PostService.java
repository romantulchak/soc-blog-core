package com.socblog.services;

import com.socblog.dto.PostByDateDTO;
import com.socblog.dto.PostDTO;
import com.socblog.dto.PostPageableDTO;
import com.socblog.models.Post;
import com.socblog.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {

    PostPageableDTO getAllPost(User user, int page);
    PostPageableDTO getAllForUser(User user, int page, User currentUser);
    ResponseEntity<?> createPost(String post, MultipartFile file) throws IOException;
    ResponseEntity<?> editPost(Post post);
    ResponseEntity<?> deletePost(Post post, User user);
    PostPageableDTO getPostsByTag(String tagName, int page, User currentUser);
    List<PostByDateDTO> getPostsForChart(User user);
    PostDTO getPostsBy(Post post);
    PostDTO setLike(Long postId, Long currentUserId);
}
