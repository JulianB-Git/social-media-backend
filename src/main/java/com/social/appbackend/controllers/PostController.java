package com.social.appbackend.controllers;

import com.social.appbackend.model.Post;
import com.social.appbackend.model.User;
import com.social.appbackend.model.requests.CreatePostRequest;
import com.social.appbackend.model.response.PostDTO;
import com.social.appbackend.model.response.UserDTO;
import com.social.appbackend.security.UserDetailsImpl;
import com.social.appbackend.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/find/{postId}")
    public String getPost(@PathVariable long postId){
        return "";
    }

    @GetMapping("/")
    public ResponseEntity<?> getPosts(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().body(convertPostsToPostsDTO(postService.getPosts(userDetails.getUsername())));
    }

    @PostMapping("/")
    public ResponseEntity<?> addPost(@RequestBody PostDTO postDTO){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post savedPost = postService.addPost(convertPostDTOToPost(postDTO), userDetails.getUsername());
        return ResponseEntity.status(201).body(convertPostToPostDTO(savedPost));
    }

    private List<PostDTO> convertPostsToPostsDTO(List<Post> posts){
        List<PostDTO> postDTOList = new ArrayList<>();

        posts.forEach((post -> {
            postDTOList.add(convertPostToPostDTO(post));
        }));

        return postDTOList;
    }

    private Post convertPostDTOToPost(PostDTO postDTO){
        Post post = new Post();
        BeanUtils.copyProperties(postDTO, post, "user");

        if (postDTO.getUuid().isEmpty()){
            post.setUuid(UUID.randomUUID().toString());
        }

        post.setCreatedAt(LocalDateTime.now());

        return post;
    }

    private PostDTO convertPostToPostDTO(Post post){
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO, "user");
        postDTO.setUser(convertUserToUserDTO(post.getUser()));
        return postDTO;
    }

    private UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

}
