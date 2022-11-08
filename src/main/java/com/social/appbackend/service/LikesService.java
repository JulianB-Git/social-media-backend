package com.social.appbackend.service;

import com.social.appbackend.model.Comment;
import com.social.appbackend.model.Likes;
import com.social.appbackend.model.Post;
import com.social.appbackend.model.User;
import com.social.appbackend.repositories.LikesRepository;
import com.social.appbackend.repositories.PostRepository;
import com.social.appbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikesService {

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    public List<Long> getLikesByPost(long postId){
        List<Likes> likes = likesRepository.findAllByPostId(postId);
        List<Long> userIdsOfLikes = new ArrayList<>();

        likes.forEach((like) -> {
            userIdsOfLikes.add(like.getUser().getId());
        });

        return userIdsOfLikes;
    }

    public void addLike(long postId, String username) {
        Likes likes = new Likes();
        User user = userRepository.findByUsername(username);
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post post = optionalPost.orElseThrow();

        likes.setPost(post);
        likes.setUser(user);

        Likes savedLike = likesRepository.save(likes);
    }

    public void deleteLike(long postId, String username) {
        User user = userRepository.findByUsername(username);
        Likes likes = likesRepository.findByUserIdEqualsAndPostIdEquals(user.getId(), postId);

        likesRepository.delete(likes);
    }
}
