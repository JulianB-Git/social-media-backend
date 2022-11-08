package com.social.appbackend.repositories;

import com.social.appbackend.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    List<Likes> findAllByPostId(long postId);
    Likes findByUserIdEqualsAndPostIdEquals(long userId, long postId);
}
