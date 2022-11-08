package com.social.appbackend.service;

import com.social.appbackend.model.User;
import com.social.appbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUser(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElseThrow();
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, long userId) {
        User updatingUser = findUser(userId);

        updatingUser.setName(user.getName());
        updatingUser.setCity(user.getCity());
        updatingUser.setWebsite(user.getWebsite());

        userRepository.save(updatingUser);
    }
}
