package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.Feedback;

import java.util.ArrayList;
import java.util.List;

public class HttpFeedbackReply  {

    private int feedbackID;
    private String title;
    private String body;
    private int date;

    public HttpFeedbackReply(int feedbackID,String title,String body, int date)
    {
        this.feedbackID = feedbackID;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackID = feedbackId;
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

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
