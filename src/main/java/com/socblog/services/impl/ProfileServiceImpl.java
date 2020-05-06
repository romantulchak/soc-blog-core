package com.socblog.services.impl;

import com.socblog.dto.UserDTO;
import com.socblog.models.User;
import com.socblog.repo.UserRepo;
import com.socblog.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    private UserRepo userRepo;
    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    public ProfileServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO getDataForUser(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getNew(), user.getFirstName(), user.getLastName(), user.getCity(), user.getBirthDay(), user.getAvatar(), user.getCountry(), user.getPlaceOfWork(), user.getGender(), user.getPosts());
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


    public List<UserDTO>users(){
      return userRepo.users();
    }
    public UserDTO userById(User user){
        return new UserDTO(user);
    }
}
