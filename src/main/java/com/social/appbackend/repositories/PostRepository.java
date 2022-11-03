package com.social.appbackend.repositories;

import com.social.appbackend.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query("SELECT p, u.id, u.name, u.profilePic FROM Post p JOIN User u ON (u.id = p.userId)" +
//            "LEFT JOIN FriendRelationship r ON (p.userId = r.followedUser) WHERE r.followerUser = ?1 OR p.userId = ?2")
@Query("SELECT p, u.id, u.name, u.profilePic FROM Post p JOIN User u ON (u.id = p.user.id)" +
        "LEFT JOIN FriendRelationship r ON (p.user.id = r.followedUser.id) WHERE r.followerUser.id = ?1 OR p.user.id = ?2")
    List<Post> findAllPostsWithFriends(long userId, long followedUserId, Sort sort);
}
