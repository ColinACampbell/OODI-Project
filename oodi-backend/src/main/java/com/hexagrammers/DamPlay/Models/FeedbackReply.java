package com.hexagrammers.DamPlay.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "feedback_replies")
public class FeedbackReply {
    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String body;

    @ManyToOne()
    private Feedback feedback;

    public FeedbackReply() { }

    public FeedbackReply(String title,String body){

        this.title = title;
        this.body = body;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @JsonIgnore()
    public Feedback getFeedback() {
        return feedback;
    }
}
