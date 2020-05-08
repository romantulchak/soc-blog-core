package com.socblog.services.impl;

import com.socblog.dto.NotificationBoxDTO;
import com.socblog.dto.NotificationDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.Notification;
import com.socblog.models.NotificationBox;
import com.socblog.models.User;
import com.socblog.repo.NotificationBoxRepo;
import com.socblog.repo.NotificationRepo;
import com.socblog.repo.UserRepo;
import com.socblog.services.ProfileService;
import com.socblog.sockets.PostMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private NotificationBoxRepo notificationBoxRepo;
    private NotificationRepo notificationRepo;

    private UserRepo userRepo;
    @Value("${upload.path}")
    private String uploadPath;


    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ProfileServiceImpl(UserRepo userRepo, SimpMessagingTemplate simpMessagingTemplate, NotificationBoxRepo notificationBoxRepo, NotificationRepo notificationRepo){
        this.userRepo = userRepo;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.notificationBoxRepo = notificationBoxRepo;
        this.notificationRepo = notificationRepo;

    }

    @Override
    public UserDTO getDataForUser(Long userId ) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return new UserDTO(user);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> setUserAvatar(String avatar, User user)throws IOException {
        String base64Image = avatar.split(",")[1];
        String avatarPath = user.getUsername() + UUID.randomUUID() + ".png";
        byte[] decodeImage = Base64.getDecoder().decode(base64Image.getBytes(StandardCharsets.UTF_8));
        Path destinationFile = Paths.get(uploadPath, avatarPath);
        Files.write(destinationFile, decodeImage);
        user.setAvatar("http://localhost:8080/avatars_min/" + avatarPath);
        userRepo.save(user);

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateUserData(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername()).orElse(null);
        user.setNew(false);
        user.setRoles(userFromDb.getRoles());
        user.setPassword(userFromDb.getPassword());
        userRepo.save(user);
        return new ResponseEntity<>("User data was updated", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> startFollowing(User user, User currentUser) {
        if(user != null && currentUser != null) {
            currentUser.getSubscriptions().add(user);
            userRepo.save(currentUser);
            this.simpMessagingTemplate.convertAndSend("/topic/update", new PostMessage("startFollowing", user.getId(), currentUser.getId()));
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        }
        return new ResponseEntity<>("Something wrong!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> stopFollowing(User user, User currentUser) {
        if (user != null && currentUser != null){
            currentUser.getSubscriptions().remove(user);
            userRepo.save(currentUser);
            this.simpMessagingTemplate.convertAndSend("/topic/update", new PostMessage("startFollowing", user.getId(), currentUser.getId()));
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }

        return new ResponseEntity<>("Something wrong!", HttpStatus.OK);
    }

    @Override
    public List<UserDTO> getSubscriptions(User user, User currentUser) {
      List<UserDTO> users = new ArrayList<>();
      List<User> usersFromBd = userRepo.findAllBySubscriptions(user);
      usersFromBd.forEach(e->{
        users.add(new UserDTO(e, currentUser));
      });
        return users;
    }

    @Override
    public NotificationBoxDTO getNotificationsForUser(User user) {

        System.out.println(user.getNotificationBox().getNotifications());
        return notificationBoxRepo.fin(user.getNotificationBox());
    }

    @Override
    public NotificationBoxDTO readNotification(NotificationBox notificationBox, Notification notification) {
        notification.setRead(true);
        notificationRepo.save(notification);

        return notificationBoxRepo.fin(notificationBox);
    }


    public List<UserDTO>users(){
      return userRepo.users();
    }
    public UserDTO userById(User user, User userInSystem){
        System.out.println(user + " " + userInSystem);

        return new UserDTO(user, userInSystem);
    }
}
