package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.Feedback;

import java.util.List;

public class HttpFeedbackBody extends Feedback {

    private int assetID;

    public HttpFeedbackBody(String title,String body, int date)
    {
        super(title,body,date,null);
        this.assetID = assetID;
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }
}
