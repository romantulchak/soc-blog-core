package com.socblog.services;

import com.socblog.dto.UserDTO;
import com.socblog.models.User;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ProfileService {
    UserDTO getDataForUser(Long userId);
    ResponseEntity<?> setUserAvatar(String avatar, User user) throws IOException;
    ResponseEntity<?> updateUserData(User user);
    ResponseEntity<?> startFollowing(User user, User currentUser);
    ResponseEntity<?> stopFollowing(User user, User currentUser);
    List<UserDTO> getSubscriptions(User user, User currentUser);
}
