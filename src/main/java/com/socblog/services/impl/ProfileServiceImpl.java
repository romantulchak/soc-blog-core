package com.socblog.services.impl;

import com.socblog.dto.NotificationBoxDTO;
import com.socblog.dto.NotificationDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.*;
import com.socblog.models.enums.ENotification;
import com.socblog.models.enums.ERole;
import com.socblog.repo.NotificationBoxRepo;
import com.socblog.repo.NotificationRepo;
import com.socblog.repo.UserRepo;
import com.socblog.services.ProfileService;
import com.socblog.sockets.PostMessage;
import com.socblog.utils.FileSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final NotificationBoxRepo notificationBoxRepo;
    private final NotificationRepo notificationRepo;

    private final UserRepo userRepo;
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.path.avatarFull}")
    private String fullUploadPath;

    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ProfileServiceImpl(UserRepo userRepo,
                              SimpMessagingTemplate simpMessagingTemplate,
                              NotificationBoxRepo notificationBoxRepo,
                              NotificationRepo notificationRepo
                              ){
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
    public ResponseEntity<?> setUserAvatar(String avatar, User user, MultipartFile multipartFile)throws IOException {
        String avatarPath = bast64ToFile(avatar, user);
        user.setAvatar("http://localhost:8080/avatars_min/" + avatarPath);
        if(multipartFile.getSize() < 5242880) {
            user.getImages().add(new Image(FileSaver.saveFile(multipartFile, fullUploadPath, "avatars_full")));
        }else{
            System.out.println("Need to compress");
        }
        userRepo.save(user);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    private String bast64ToFile(String avatar, User user) throws IOException {
        String base64Image = avatar.split(",")[1];
        String avatarPath = user.getUsername() + UUID.randomUUID() + ".png";
        byte[] decodeImage = Base64.getDecoder().decode(base64Image.getBytes(StandardCharsets.UTF_8));
        Path destinationFile = Paths.get(uploadPath, avatarPath);
        Files.write(destinationFile, decodeImage);
        return avatarPath;
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
            Notification notification = new Notification("started following you.", user.getNotificationBox(), currentUser, ENotification.START_FOLLOWING);
            userRepo.save(currentUser);
            notificationRepo.save(notification);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        }
        return new ResponseEntity<>("Something wrong!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> stopFollowing(User user, User currentUser) {
        if (user != null && currentUser != null){
            currentUser.getSubscriptions().remove(user);
            userRepo.save(currentUser);
            this.simpMessagingTemplate.convertAndSend("/topic/update", new PostMessage("stopFollowing", user.getId(), currentUser.getId()));
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }

        return new ResponseEntity<>("Something wrong!", HttpStatus.OK);
    }

    @Override
    public List<UserDTO> getSubscriptions(User user, User currentUser) {
        return userRepo.findAllBySubscriptions(user).stream().map(x-> convertToDto(x, currentUser)).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getSubscribers(User user, User currentUser) {
        return userRepo.findAllBySubscribers(user).stream().map(x->convertToDto(x, currentUser)).collect(Collectors.toList());
    }

    private UserDTO convertToDto(User user, User currentUser){
        return new UserDTO(user,currentUser);

    }


    @Override
    public NotificationBoxDTO getNotificationsForUser(User user) {
        NotificationBoxDTO notificationBoxDTO = notificationBoxRepo.fin(user.getNotificationBox());
        List<NotificationDTO> notificationDTOS = notificationBoxDTO.getNotifications().stream().map(x->coNotificationDTO(x, user)).collect(Collectors.toList());
        return new NotificationBoxDTO(notificationBoxDTO, notificationDTOS);
    }
    private NotificationDTO coNotificationDTO(Notification notification, User currentUser){
        return new NotificationDTO(notification, new UserDTO(notification.getUser(), currentUser));
    }

    @Override
    public NotificationBoxDTO readNotification(NotificationBox notificationBox, Notification notification) {
        notification.setRead(true);
        notificationRepo.save(notification);
        return notificationBoxRepo.fin(notificationBox);
    }

    public UserDTO userById(User user, User userInSystem){
        return new UserDTO(user, userInSystem);
    }


    @Override
    public void setOnline(Long userId, boolean isOnline) {
        User user = userRepo.findById(userId).orElse(null);
        if(user != null){
            user.setOnline(isOnline);
            this.userRepo.save(user);
        }
    }

    @Override
    public Set<UserDTO> explorePeople(User user) {
        List<User> users = userRepo.explorePeople( user.getId(), user.getCountry(), user.getCity(), user);
        Random r = new Random();
        return users.stream().map(x->convertToDto(x, user)).skip(r.nextInt(users.size() - 1)).limit(25).collect(Collectors.toSet());
    }

    @Override
    public ResponseEntity<?> addInterests(Tag tag, User user) {
        if(user != null && tag != null){
           if(user.getInterests().contains(tag)){
               user.getInterests().remove(tag) ;
           }else{
               user.getInterests().add(tag);
            }
            userRepo.save(user);
            return new ResponseEntity<>("Ok", HttpStatus.OK);

        }
        return new ResponseEntity<>("Something wrong", HttpStatus.OK);
    }
}
