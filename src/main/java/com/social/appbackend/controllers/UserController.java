package com.social.appbackend.controllers;

import com.social.appbackend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/find/{userId}")
    public String getUser(@PathVariable long userId){
        return "";
    }
}
