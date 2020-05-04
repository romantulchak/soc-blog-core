package com.socblog.services.impl;

import com.socblog.models.Role;
import com.socblog.models.User;
import com.socblog.models.enums.ERole;
import com.socblog.payload.request.LoginRequest;
import com.socblog.payload.request.SignupRequest;
import com.socblog.payload.response.JwtResponse;
import com.socblog.payload.response.MessageResponse;
import com.socblog.repo.RoleRepo;
import com.socblog.repo.UserRepo;
import com.socblog.security.jwt.JwtUtils;
import com.socblog.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepo userRepo, RoleRepo roleRepo,
                           PasswordEncoder encoder,
                           JwtUtils jwtUtils){
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (userRepo.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepo.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        setRoles(strRoles, roles);

        user.setRoles(roles);
        userRepo.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private void setRoles(Set<String> strRoles, Set<Role> roles) {
        if (strRoles == null) {
            userRole(roles, ERole.ROLE_USER);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        userRole(roles, ERole.ROLE_ADMIN);
                        userRole(roles, ERole.ROLE_USER);
                        break;
                    case "mod":
                        userRole(roles, ERole.ROLE_MODERATOR);
                        break;
                    default:
                        userRole(roles, ERole.ROLE_USER);
                }
            });
        }
    }

    private void userRole(Set<Role> roles, ERole roleUser) {
        Role userRole = roleRepo.findByName(roleUser)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
    }
}
