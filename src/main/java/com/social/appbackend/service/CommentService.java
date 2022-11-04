package com.social.appbackend.service;

import com.social.appbackend.model.Comment;
import com.social.appbackend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getCommentByPostId(long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return comments;
    }
}
