package com.hexagrammers.DamPlay.Models;

import javax.persistence.*;

@Entity
@Table(name = "asset_recipients")
public class AssetRecipient {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private Asset asset;

    public AssetRecipient()
    {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Asset getReceivedAsset() {
        return asset;
    }

    public User getRecipient() {
        return user;
    }

    public void setReceivedAsset(Asset receivedAsset) {
        this.asset = receivedAsset;
    }

    public void setRecipient(User recipient) {
        this.user = recipient;
    }
}
