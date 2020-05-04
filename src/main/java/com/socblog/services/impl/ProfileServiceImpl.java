package com.socblog.services.impl;

import com.socblog.dto.UserDTO;
import com.socblog.models.User;
import com.socblog.repo.UserRepo;
import com.socblog.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private UserRepo userRepo;

    @Autowired
    public ProfileServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO getDataForUser(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
        }
        return null;
    }
}
