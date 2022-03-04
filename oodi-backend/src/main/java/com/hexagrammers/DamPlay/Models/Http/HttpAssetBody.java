package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.Asset;
import java.util.List;

public class HttpAssetBody extends Asset {
    private List<Integer> assetRecipients;

    public HttpAssetBody(String title, String description, String assetLink, String reviewedBy,List<Integer> recipients)
    {
        super(title, description, assetLink, reviewedBy);
        this.assetRecipients = recipients;
    }

    public List<Integer> getAssetRecipients() {
        return assetRecipients;
    }

    public void setAssetRecipients(List<Integer> recipients) {
        this.assetRecipients = recipients;
    }
}
