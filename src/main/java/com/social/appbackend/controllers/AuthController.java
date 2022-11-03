package com.social.appbackend.controllers;

import com.social.appbackend.constants.LoggerMessage;
import com.social.appbackend.model.User;
import com.social.appbackend.model.requests.CreateUserRequest;
import com.social.appbackend.model.requests.LoginRequest;
import com.social.appbackend.model.response.JwtResponse;
import com.social.appbackend.repositories.UserRepository;
import com.social.appbackend.security.UserDetailsImpl;
import com.social.appbackend.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException e){
            return ResponseEntity.badRequest().body(new ApiError("Username or password incorrect", null));
        }


        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getProfilePic(),
                userDetails.getCoverPic(),
                userDetails.getCity(),
                userDetails.getWebsite()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());

        if (userRepository.findByUsername(user.getUsername()) != null) {
            logger.error(LoggerMessage.SIGNUP_ERROR_INVALID_USERNAME);
            return ResponseEntity.badRequest().body(new ApiError(LoggerMessage.SIGNUP_ERROR_INVALID_USERNAME, null));
        }

        if(signUpRequest.getPassword().length()< 7 ){
            logger.error(LoggerMessage.SIGNUP_ERROR_PASSWORD_LENGTH);
            return ResponseEntity.badRequest().body(new ApiError(LoggerMessage.SIGNUP_ERROR_PASSWORD_LENGTH, null));
        }

        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());

        userRepository.save(user);
        logger.info(LoggerMessage.SIGNUP_SUCCESS + user.getUsername());
        return ResponseEntity.ok(user);
    }
}
