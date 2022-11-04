package com.social.appbackend.controllers;

import com.amazonaws.HttpMethod;
import com.social.appbackend.service.aws.AwsS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

}
