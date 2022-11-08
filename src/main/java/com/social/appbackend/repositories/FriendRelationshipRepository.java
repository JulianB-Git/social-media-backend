package com.social.appbackend.repositories;

import com.social.appbackend.model.FriendRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRelationshipRepository extends JpaRepository<FriendRelationship, Long> {

    List<FriendRelationship> findAllByFollowedUserId(long userId);
    FriendRelationship findByFollowerUserIdEqualsAndFollowedUserIdEquals(long followerUserId, long followedUserId);
}
