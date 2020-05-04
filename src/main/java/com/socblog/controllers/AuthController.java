package com.socblog.controllers;

import com.socblog.payload.request.LoginRequest;
import com.socblog.payload.request.SignupRequest;
import com.socblog.services.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {

    private AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService){
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
       return this.authService.authenticateUser(loginRequest);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        return this.authService.registerUser(signupRequest);
    }


}
