package com.hexagrammers.DamPlay.Models;



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
    private int date;
    private String body;

    @OneToMany(mappedBy = "feedback")
    private List<FeedbackReply> replies;

    @ManyToOne()
    Asset asset;

    public Feedback() {
        this.replies = new ArrayList<>();
    }


    public Feedback(String title,String body,int date, Asset asset){

        this.title = title;
        this.body = body;
        this.date = date;
        this.replies = new ArrayList<>();
        this.asset = asset;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
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

    public Asset getAsset() {
        return asset;
    }
}
