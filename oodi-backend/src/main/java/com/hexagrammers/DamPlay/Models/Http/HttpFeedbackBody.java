package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.Feedback;

import java.util.ArrayList;
import java.util.List;

public class HttpFeedbackBody  {

    private int assetID;
    private String title;
    private String body;

    public HttpFeedbackBody(int assetID,String title,String body)
    {
        this.assetID = assetID;
        this.title = title;
        this.body = body;
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}