package com.hexagrammers.DamPlay.Models;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

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

    @OneToMany()
    List<AssetRecipient> recipients;

    public Asset()
    {

    }

    public Asset(String title, String description,String assetLink, String reviewedBy)
    {
        this.title = title;
        this.description = description;
        this.assetLink = assetLink;
        this.reviewedBy = reviewedBy;
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
        if ( recipients == null )
        {
            recipients = new ArrayList<AssetRecipient>();
        }
        recipients.add(recipient);
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
}
