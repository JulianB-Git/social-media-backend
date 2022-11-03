package com.social.appbackend.security;

import com.social.appbackend.constants.LoggerMessage;
import com.social.appbackend.model.User;
import com.social.appbackend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            logger.error(LoggerMessage.LOGIN_ERROR_USERNAME + username);
//            throw new UsernameNotFoundException(username);
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
//    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            logger.error(LoggerMessage.LOGIN_ERROR_USERNAME + username);
            throw new UsernameNotFoundException(username);
        }

        return UserDetailsImpl.build(user);
    }
}
