package com.social.appbackend.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class FriendRelationship {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "followerUser", referencedColumnName = "id")
    @NotNull
    private User followerUser;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "followedUser", referencedColumnName = "id")
    @NotNull
    private User followedUser;

    public FriendRelationship(Long id, User followerUser, User followedUser) {
        this.id = id;
        this.followerUser = followerUser;
        this.followedUser = followedUser;
    }

    public FriendRelationship() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFollowerUser() {
        return followerUser;
    }

    public void setFollowerUser(User followerUser) {
        this.followerUser = followerUser;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }
}
