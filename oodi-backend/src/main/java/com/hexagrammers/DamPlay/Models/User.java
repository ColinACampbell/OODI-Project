package com.hexagrammers.DamPlay.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity()
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String username;
    private String email;

    @OneToMany(mappedBy = "sender")
    private List<Asset> assets;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<AssetRecipient> assetRecipients;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    List<Feedback> feedbacks = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user")
    List<FeedbackReply> feedbackReplies = new ArrayList<>();

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

    @JsonIgnore
    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedback(Feedback feedback)
    {
        this.feedbacks.add(feedback);
    }

    public void addFeedbackReply(FeedbackReply feedbackReply)
    {
        this.feedbackReplies.add(feedbackReply);
    }


}
