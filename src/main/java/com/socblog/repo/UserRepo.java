package com.socblog.repo;

import com.socblog.dto.UserDTO;
import com.socblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query(value="SELECT new com.socblog.dto.UserDTO(u) FROM User u")
    List<UserDTO> users();

}
