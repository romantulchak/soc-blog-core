package com.socblog.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.socblog.dto.NotificationBoxDTO;
import com.socblog.dto.NotificationDTO;
import com.socblog.dto.UserDTO;
import com.socblog.models.Notification;
import com.socblog.models.NotificationBox;
import com.socblog.models.User;
import com.socblog.models.Views;
import com.socblog.services.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(value = "*", maxAge = 3600)
public class ProfileController {

    private ProfileServiceImpl profileService;

    @Autowired
    public ProfileController(ProfileServiceImpl profileService){
        this.profileService = profileService;
    }

    @GetMapping("/getUserData/{userId}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(Views.UserFull.class)
    public UserDTO getUserData(@PathVariable("userId") Long userId){
        return profileService.getDataForUser(userId);
    }


    @PutMapping("/setAvatar/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> setAvatar(@RequestBody String image, @PathVariable("userId") User user) throws IOException {
        return profileService.setUserAvatar(image, user);
    }

    @PutMapping("/updateUserData")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUserData(@RequestBody User user){
        return profileService.updateUserData(user);
    }

    @GetMapping("/users")
    @JsonView(Views.UserFull.class)
    public List<UserDTO> users(){
        return profileService.users();
    }

    @GetMapping("/userById/{userId}/{currentUserById}")
    @JsonView(Views.UserFull.class)
    public UserDTO userById(@PathVariable("userId") User user, @PathVariable("currentUserById") User userInSystem){
        return profileService.userById(user,userInSystem);
    }

    @PutMapping("/startFollowing/{userId}/{currentUserById}")
    public ResponseEntity<?> startFollowing(@PathVariable("userId") User user, @PathVariable("currentUserById") User currentUser){
        return profileService.startFollowing(user, currentUser);
    }
    @PutMapping("/stopFollowing/{userId}/{currentUserById}")
    public ResponseEntity<?> stopFollowing(@PathVariable("userId") User user, @PathVariable("currentUserById") User currentUser){
        return profileService.stopFollowing(user, currentUser);
    }

    @GetMapping("/subscriptions/{userId}/{currentUser}")
    @JsonView(Views.UserSubscribeFull.class)
    public List<UserDTO> getSubscriptions(@PathVariable("userId") User user, @PathVariable("currentUser") User currentUser){
        return profileService.getSubscriptions(user, currentUser);
    }

    @GetMapping("/getNotificationsForUser/{userId}")
    @JsonView(Views.UserFull.class)
    public NotificationBoxDTO getNotificationsForUser(@PathVariable("userId") User user){
        return profileService.getNotificationsForUser(user);
    }

    @PutMapping("/readNotification/{notificationBoxId}/{notificationId}")
    @JsonView(Views.UserFull.class)
    public NotificationBoxDTO readNotification(@PathVariable("notificationBoxId") NotificationBox notificationBox, @PathVariable("notificationId")Notification notification){
        return profileService.readNotification(notificationBox, notification);
    }
}
