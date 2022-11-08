package com.social.appbackend.controllers;

import com.amazonaws.HttpMethod;
import com.social.appbackend.security.UserDetailsImpl;
import com.social.appbackend.service.aws.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/file")
public class FileController {

    private final AwsS3Service awsS3Service;

    @Autowired
    public FileController(AwsS3Service awsS3Service) {
        this.awsS3Service = awsS3Service;
    }

    @GetMapping("/generate-upload-url/{postUUID}")
    public ResponseEntity<String> generateUploadUrl(@PathVariable String postUUID) {
        return ResponseEntity.ok(
                awsS3Service.generatePreSignedUrl(postUUID, "social-benade-bucket", HttpMethod.PUT));
    }

    @GetMapping("/generate-upload-url/cover-photo")
    public ResponseEntity<String> generateUploadUrlCoverPhoto(@RequestParam String fileName) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(
                awsS3Service.generatePreSignedUrlCoverPhoto(fileName, "social-benade-bucket", HttpMethod.PUT, userDetails.getId()));
    }

    @GetMapping("/generate-upload-url/profile-photo")
    public ResponseEntity<String> generateUploadUrlProfilePhoto(@RequestParam String fileName) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(
                awsS3Service.generatePreSignedUrlProfilePhoto(fileName, "social-benade-bucket", HttpMethod.PUT, userDetails.getId()));
    }

}
