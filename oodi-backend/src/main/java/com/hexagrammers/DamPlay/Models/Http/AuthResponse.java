package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.User;

import java.util.List;

public class AuthResponse {
    private List<User> members;
    private User user;
    private String token;

    public AuthResponse(User user, List<User> members, String token)
    {
        this.members = members;
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public List<User> getMembers() {
        return members;
    }
}
