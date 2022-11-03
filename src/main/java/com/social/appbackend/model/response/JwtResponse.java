package com.social.appbackend.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtResponse {

    private String token;

    @JsonIgnore
    private String type = "Bearer";

    private long id;
    private String name;
    private String username;
    private String email;
    private String profilePic;
    private String coverPic;
    private String city;
    private String website;

    public JwtResponse(String accessToken, long id, String name, String username, String email, String profilePic, String coverPic, String city, String website) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
        this.city = city;
        this.website = website;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

}
