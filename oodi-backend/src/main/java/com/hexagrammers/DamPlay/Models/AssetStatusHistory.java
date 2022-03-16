package com.hexagrammers.DamPlay.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class AssetStatusHistory
{
    @Id()
    @GeneratedValue()
    private int id;

    @ManyToOne()
    private Asset asset;

    private AssetStatus status;
    private Long time;

    @ManyToOne()
    private User updatedBy;

    public AssetStatusHistory()
    {

    }

    public AssetStatusHistory(Asset asset, AssetStatus status, Long time, User updatedBy)
    {
        this.asset = asset;
        this.status = status;
        this.time = time;
        this.updatedBy = updatedBy;
    }

    @JsonIgnore
    public Asset getAsset() {
        return asset;
    }

    public AssetStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public Long getTime() {
        return time;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }
}
