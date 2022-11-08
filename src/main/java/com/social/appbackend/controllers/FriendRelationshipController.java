package com.social.appbackend.controllers;

import com.social.appbackend.security.UserDetailsImpl;
import com.social.appbackend.service.FriendRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relationship")
public class FriendRelationshipController {

    @Autowired
    FriendRelationshipService friendRelationshipService;

    @GetMapping
    public ResponseEntity<?> getRelationships(@RequestParam long followedUserId){
        return ResponseEntity.ok().body(friendRelationshipService.getRelationships(followedUserId));
    }

    @PostMapping
    public ResponseEntity<?> followUser(@RequestParam long followUserId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendRelationshipService.followUser(followUserId, userDetails.getUsername());
        return ResponseEntity.ok().body("User Followed");
    }

    @DeleteMapping
    public ResponseEntity<?> unfollowUser(@RequestParam long followUserId){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        friendRelationshipService.unFollowUser(followUserId, userDetails.getUsername());
        return ResponseEntity.ok().body("User unfollowed");
    }

}
