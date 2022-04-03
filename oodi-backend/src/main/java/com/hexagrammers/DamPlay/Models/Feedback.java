package com.hexagrammers.DamPlay.Models;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "feedbacks")

public class Feedback {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String body;

    @OneToMany(mappedBy = "feedback")
    private List<FeedbackReply> replies;

    @ManyToOne()
    Asset asset;

    @ManyToOne()
    User user;

    public Feedback() {
        this.replies = new ArrayList<>();
    }


    public Feedback(String title,String body,User user){

        this.title = title;
        this.body = body;
        this.replies = new ArrayList<>();
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody(){ return body;}
    public String getTitle(){return title;}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<FeedbackReply> getReplies() {
        return replies;
    }

    public void setReplies(List<FeedbackReply> replies) {
        this.replies = replies;
    }

    public void addFeedbackReply(FeedbackReply feedbackReply)
    {
        this.replies.add(feedbackReply);
    }

    @JsonIgnore
    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public String getName()
    {
        System.out.println(user);
        if (user == null)
            return "";
        return user.getEmail();
    }
}
