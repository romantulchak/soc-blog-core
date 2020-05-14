package com.socblog.repo;

import com.socblog.dto.PostDTO;
import com.socblog.models.Post;
import com.socblog.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    //@Query(value ="SELECT new com.socblog.dto.PostDTO(p) FROM Post p where p.user = ?1 order by p.id DESC ")
    //List<PostDTO> findAllForUser(User user);

    @Query(value = "SELECT p FROM Post p where p.user = ?1 order by p.createdDate DESC ")
    Page<Post> findAllForUser(User user, Pageable pageable);

   // @Query(value = "SELECT p FROM Post p where p.user =:user and :user in :userSub")
   // Page<Post> findAllBySubscriptions(@Param("user")User user, @Param("userSub") Set<User> subscriptions, Pageable pageable);
  // @Query(value = "SELECT p FROM Post p where p.user = :user and :user in :userSub")
   // List<Post> findAllBySubscriptions(@Param("user")User user, @Param("userSub") Set<User> subscriptions);


   @Query(value = "SELECT p FROM Post p where p.user in (SELECT u FROM User u where u in :subscriptions) order by p.createdDate DESC ")
   Page<Post> findAllBySubscriptions(@Param("subscriptions") Set<User> subscriptions, Pageable pageable);

   //@Query(value = "SELECT p FROM Post p where p in :posts")
   //Page<Post> findAllForUsers(@Param("posts")List<Post> posts, Pageable pageable);

}
