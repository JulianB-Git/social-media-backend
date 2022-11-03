package com.social.appbackend.model;

import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "createdAt", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    public Post(Long id, String description, String imageUrl, User user, LocalDateTime createdAt) {
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.user = user;
        this.createdAt = createdAt;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
