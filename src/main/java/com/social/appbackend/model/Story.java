package com.social.appbackend.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Story {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    public Story(Long id, String imageUrl, User user) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public Story() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
