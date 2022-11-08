package com.social.appbackend.controllers;

import com.social.appbackend.model.User;
import com.social.appbackend.model.requests.UpdateUserDTO;
import com.social.appbackend.model.response.UserDTO;
import com.social.appbackend.model.response.UserProfileDTO;
import com.social.appbackend.repositories.UserRepository;
import com.social.appbackend.security.UserDetailsImpl;
import com.social.appbackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/find/{userId}")
    public ResponseEntity<?> getUser(@PathVariable long userId){
        return ResponseEntity.ok().body(convertUserToUserDTO(userService.findUser(userId)));
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userService.updateUser(convertUserDTOToUser(updateUserDTO), userDetails.getId());
        return ResponseEntity.ok().body("User updated");
    }

    private UserProfileDTO convertUserToUserDTO(User user) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        BeanUtils.copyProperties(user, userProfileDTO);
        return userProfileDTO;
    }

    private User convertUserDTOToUser(UpdateUserDTO updateUserDTO) {
        User user = new User();
        BeanUtils.copyProperties(updateUserDTO, user);
        return user;
    }
}
