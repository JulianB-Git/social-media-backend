package com.social.appbackend.controllers;

import com.social.appbackend.model.Post;
import com.social.appbackend.model.response.PostDTO;
import com.social.appbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping()
    public ResponseEntity<?> getCommentsForPost(@RequestParam long postId){
        commentService.getCommentByPostId(postId);
        return ResponseEntity.ok().body("");
    }

    private List<PostDTO> convertCommentsToCommentsDTO(List<Post> posts){
        List<PostDTO> postDTOList = new ArrayList<>();

//        posts.forEach((post -> {
//            postDTOList.add(convertPostToPostDTO(post));
//        }));

        return postDTOList;
    }

}
