package com.social.appbackend.service;

import com.social.appbackend.model.Comment;
import com.social.appbackend.model.Post;
import com.social.appbackend.model.User;
import com.social.appbackend.repositories.CommentRepository;
import com.social.appbackend.repositories.PostRepository;
import com.social.appbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    public List<Comment> getCommentByPostId(long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId, Sort.by("createdAt").descending());

        return comments;
    }

    public Comment addComment(Comment comment, long postId, String username) {
        User user = userRepository.findByUsername(username);
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post post = optionalPost.orElseThrow();

        comment.setUser(user);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        return savedComment;
    }
}
