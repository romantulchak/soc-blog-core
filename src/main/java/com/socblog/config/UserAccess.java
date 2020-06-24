package com.socblog.config;

import com.socblog.models.User;
import com.socblog.services.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserAccess {



    public boolean hasUserId(Authentication authentication, Long userId){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        return user != null && user.getId().equals(userId);
    }

}
