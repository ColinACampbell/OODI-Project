package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.AssetStatus;

import java.util.List;

public class HttpAssetBody extends Asset {
    private List<Integer> assetRecipients;

    private String status;

    public HttpAssetBody(String title, String description, String assetLink, String reviewedBy,List<Integer> recipients, String status)
    {
        super(title, description, assetLink, reviewedBy);
        this.status = status;
        this.assetRecipients = recipients;
        if (status == null){
            this.status = AssetStatus.SUBMITTED.name();
            this.setStatus(AssetStatus.SUBMITTED);
        } else
            this.setStatus(AssetStatus.valueOf(status));
    }

    public List<Integer> getAssetRecipients() {
        return assetRecipients;
    }

    public void setAssetRecipients(List<Integer> recipients) {
        this.assetRecipients = recipients;
    }

    public String getAssetStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
