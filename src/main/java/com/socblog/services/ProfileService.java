package com.socblog.services;

import com.socblog.dto.NotificationBoxDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface ProfileService {
    UserDTO getDataForUser(Long userId);
    ResponseEntity<?> setUserAvatar(String avatar, User user, MultipartFile file) throws IOException;
    ResponseEntity<?> updateUserData(User user, String username);
    ResponseEntity<?> startFollowing(User user, User currentUser);
    ResponseEntity<?> stopFollowing(User user, User currentUser);
    List<UserDTO> getSubscriptions(User user, User currentUser);
    List<UserDTO> getSubscribers(User user, User currentUser);
    NotificationBoxDTO getNotificationsForUser(User user);
    NotificationBoxDTO readNotification(NotificationBox notificationBox, Notification notification);
    void setOnline(Long userId, boolean isOnline);
    Set<UserDTO> explorePeople(User user);
    ResponseEntity<?> addInterests(Tag tag, String username);
    ResponseEntity<?> changePassword(String user, String oldPassword, String newPassword);
    ResponseEntity<?> deleteUserImage(User user, Image image);
    void removeAccount(User user);
}
