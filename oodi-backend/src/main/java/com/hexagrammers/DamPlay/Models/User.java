package com.hexagrammers.DamPlay.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String username;
    private String email;

    @OneToMany(mappedBy = "sender")
    private List<Asset> assets;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<AssetRecipient> assetRecipient;

    public User(String email, String username,String password)
    {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String username,String password)
    {
        this.username = username;
        this.password = password;
    }

    public User()
    {

    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
