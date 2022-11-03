package com.social.appbackend.service;

import com.social.appbackend.model.Post;
import com.social.appbackend.model.User;
import com.social.appbackend.model.requests.CreatePostRequest;
import com.social.appbackend.repositories.PostRepository;
import com.social.appbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public List<Post> getPosts(String username){
        User user = userRepository.findByUsername(username);
        List<Post> posts = postRepository.findAllPostsWithFriends(user.getId(), user.getId(), Sort.by("createdAt").descending());

        return posts;
    }

    public Post addPost(Post post, String username) {
        User user = userRepository.findByUsername(username);
        post.setUser(user);

        Post savedPost = postRepository.save(post);
        return savedPost;
    }

}
