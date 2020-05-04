package com.socblog.controllers;

import com.socblog.dto.UserDTO;
import com.socblog.models.User;
import com.socblog.services.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public UserDTO getUserData(@PathVariable("userId") Long userId){
        return profileService.getDataForUser(userId);
    }

}
