package com.social.appbackend.service.aws;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.social.appbackend.model.User;
import com.social.appbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AwsS3Service {

    private final AmazonS3 amazonS3;

    @Autowired
    public AwsS3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Autowired
    UserService userService;

    public String generatePreSignedUrl(String filePath,
                                       String bucketName,
                                       HttpMethod httpMethod) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10); //validity of 10 minutes

        return amazonS3.generatePresignedUrl(bucketName, filePath, calendar.getTime(), httpMethod).toString();
    }

    public String generatePreSignedUrlCoverPhoto(String filePath,
                                       String bucketName,
                                       HttpMethod httpMethod, long userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10); //validity of 10 minutes

        User user = userService.findUser(userId);
        user.setCoverPic("https://social-benade-bucket.s3.amazonaws.com/"+ filePath);

        userService.updateUser(user);

        return amazonS3.generatePresignedUrl(bucketName, filePath, calendar.getTime(), httpMethod).toString();
    }

    public String generatePreSignedUrlProfilePhoto(String filePath,
                                                 String bucketName,
                                                 HttpMethod httpMethod, long userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 10); //validity of 10 minutes

        User user = userService.findUser(userId);
        user.setProfilePic("https://social-benade-bucket.s3.amazonaws.com/"+ filePath);

        userService.updateUser(user);

        return amazonS3.generatePresignedUrl(bucketName, filePath, calendar.getTime(), httpMethod).toString();
    }
}
