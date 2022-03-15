package com.hexagrammers.DamPlay.Models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String name;
    private String email;
    private String position;

    @OneToMany(mappedBy = "sender")
    private List<Asset> assets;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<AssetRecipient> assetRecipients;


    public User(String email, String name,String password, String position)
    {
        this.email = email;
        this.name = name;
        this.password = password;
        this.position = position;
    }

    public User(String name,String password)
    {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
