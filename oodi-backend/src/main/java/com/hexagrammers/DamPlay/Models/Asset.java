package com.hexagrammers.DamPlay.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue
    private int id;

    String title;
    String description;
    String assetLink;

    String reviewedBy;

    @ManyToOne()
    User sender;

    @OneToMany(mappedBy = "asset")
    List<AssetRecipient> recipients;

    @OneToMany(mappedBy = "asset")
    List<Feedback> feedbacks;

    public Asset()
    {
        this.recipients = new ArrayList<>();
    }

    public Asset(String title, String description,String assetLink, String reviewedBy)
    {
        this.title = title;
        this.description = description;
        this.assetLink = assetLink;
        this.reviewedBy = reviewedBy;
        this.recipients = new ArrayList<>();
        this.feedbacks = new ArrayList<>();
    }


    public void setAssetLink(String assetLink) {
        this.assetLink = assetLink;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReviewedBy(String reviewedBy) {
        this.reviewedBy = reviewedBy;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssetLink() {
        return assetLink;
    }

    public String getDescription() {
        return description;
    }

    public String getReviewedBy() {
        return reviewedBy;
    }

    public String getTitle() {
        return title;
    }

    public void addRecipient(AssetRecipient recipient)
    {
        recipients.add(recipient);
    }

    public void setRecipients(List<AssetRecipient> recipients) {
        this.recipients = recipients;
    }

    public List<AssetRecipient> getRecipients() {
        return recipients;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void addFeedbackReply(Feedback feedback)
    {
        this.feedbacks.add(feedback);
    }
}
