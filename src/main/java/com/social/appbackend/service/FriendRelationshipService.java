package com.social.appbackend.service;

import com.social.appbackend.model.FriendRelationship;
import com.social.appbackend.model.Likes;
import com.social.appbackend.model.Post;
import com.social.appbackend.model.User;
import com.social.appbackend.repositories.FriendRelationshipRepository;
import com.social.appbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendRelationshipService {

    @Autowired
    FriendRelationshipRepository friendRelationshipRepository;

    @Autowired
    UserRepository userRepository;

    public List<Long> getRelationships(long followedUserId) {
        List<FriendRelationship> friendRelationships = friendRelationshipRepository.findAllByFollowedUserId(followedUserId);
        List<Long> relationshipData = new ArrayList<>();

        friendRelationships.forEach((relationship) -> {
            relationshipData.add(relationship.getFollowerUser().getId());
        });

        return relationshipData;

    }

    public void followUser(long followUserId, String username) {
        FriendRelationship friendRelationship = new FriendRelationship();
        User user = userRepository.findByUsername(username);
        Optional<User> optionalUser = userRepository.findById(followUserId);
        User foundUser = optionalUser.orElseThrow();

        friendRelationship.setFollowerUser(user);
        friendRelationship.setFollowedUser(foundUser);

        friendRelationshipRepository.save(friendRelationship);
    }

    public void unFollowUser(long followUserId, String username) {
        User user = userRepository.findByUsername(username);
        FriendRelationship friendRelationship = friendRelationshipRepository.findByFollowerUserIdEqualsAndFollowedUserIdEquals(user.getId(), followUserId);

        friendRelationshipRepository.delete(friendRelationship);
    }
}
