package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.Asset;
import java.util.List;

public class HttpAssetBody extends Asset {
    private List<Integer> recipients;

    public HttpAssetBody(String title, String description, String assetLink, String reviewedBy,List<Integer> recipients)
    {
        super(title, description, assetLink, reviewedBy);
        this.recipients = recipients;
    }

    public List<Integer> getAssetRecipients() {
        return recipients;
    }

    public void setRecipients(List<Integer> recipients) {
        this.recipients = recipients;
    }
}
