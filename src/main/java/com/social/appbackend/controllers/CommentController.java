package com.social.appbackend.controllers;

import com.social.appbackend.model.Comment;
import com.social.appbackend.model.Post;
import com.social.appbackend.model.User;
import com.social.appbackend.model.response.CommentDTO;
import com.social.appbackend.model.response.PostDTO;
import com.social.appbackend.model.response.UserDTO;
import com.social.appbackend.security.UserDetailsImpl;
import com.social.appbackend.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping()
    public ResponseEntity<?> getCommentsForPost(@RequestParam long postId){
        List<Comment> comments = commentService.getCommentByPostId(postId);
        return ResponseEntity.ok().body(convertCommentsToCommentsDTO(comments));
    }

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO, @RequestParam long postId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = commentService.addComment(convertCommentDTOToComment(commentDTO), postId, userDetails.getUsername());

        return ResponseEntity.status(201).body("Comment created");
    }

    private Comment convertCommentDTOToComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        comment.setCreatedAt(LocalDateTime.now());

        return comment;
    }

    private List<CommentDTO> convertCommentsToCommentsDTO(List<Comment> comments){
        List<CommentDTO> commentDTOList = new ArrayList<>();

        comments.forEach((comment) -> {
            commentDTOList.add(convertCommentToCommentDTO(comment));
        });

        return commentDTOList;
    }

    private CommentDTO convertCommentToCommentDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(comment, commentDTO, "user");

        commentDTO.setUser(convertUserToUserDTO(comment.getUser()));

        return commentDTO;
    }

    private UserDTO convertUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

}
