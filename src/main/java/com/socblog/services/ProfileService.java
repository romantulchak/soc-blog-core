package com.socblog.services;

import com.socblog.dto.UserDTO;
import com.socblog.models.User;

public interface ProfileService {
    UserDTO getDataForUser(Long userId);
}
