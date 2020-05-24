package com.socblog.services;

import com.socblog.dto.AvatarsDTO;
import com.socblog.dto.NotificationBoxDTO;
import com.socblog.dto.NotificationDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.Notification;
import com.socblog.models.NotificationBox;
import com.socblog.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProfileService {
    UserDTO getDataForUser(Long userId);
    ResponseEntity<?> setUserAvatar(String avatar, User user, MultipartFile file) throws IOException;
    ResponseEntity<?> updateUserData(User user);
    ResponseEntity<?> startFollowing(User user, User currentUser);
    ResponseEntity<?> stopFollowing(User user, User currentUser);
    List<UserDTO> getSubscriptions(User user, User currentUser);
    List<UserDTO> getSubscribers(User user, User currentUser);
    NotificationBoxDTO getNotificationsForUser(User user);
    NotificationBoxDTO readNotification(NotificationBox notificationBox, Notification notification);
    void setOnline(Long userId, boolean isOnline);
}
