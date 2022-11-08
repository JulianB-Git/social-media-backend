package com.social.appbackend.controllers;

import com.social.appbackend.model.Likes;
import com.social.appbackend.model.User;
import com.social.appbackend.model.response.LikesDTO;
import com.social.appbackend.model.response.UserDTO;
import com.social.appbackend.security.UserDetailsImpl;
import com.social.appbackend.service.LikesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/likes")
public class LikesController {

    @Autowired
    LikesService likesService;

    @GetMapping
    public List<Long> getLikesForPost(@RequestParam long postId){
        return likesService.getLikesByPost(postId);
    }

    @PostMapping()
    public ResponseEntity<?> addLike(@RequestParam long postId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        likesService.addLike(postId, userDetails.getUsername());
        return ResponseEntity.ok().body("Post Liked");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLike(@RequestParam long postId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        likesService.deleteLike(postId, userDetails.getUsername());
        return ResponseEntity.ok().body("Like removed");
    }

    private List<LikesDTO> convertLikesListToLikesDTOList(List<Likes> likes){
        List<LikesDTO> likesDTOS = new ArrayList<>();

        likes.forEach((like) -> {
            likesDTOS.add(convertLikeToLikeDTO(like));
        });

        return likesDTOS;
    }

    private LikesDTO convertLikeToLikeDTO(Likes like){
        LikesDTO likesDTO = new LikesDTO();

        BeanUtils.copyProperties(like, likesDTO, "post");
        likesDTO.setUserId(like.getUser().getId());

        return likesDTO;
    }
}
