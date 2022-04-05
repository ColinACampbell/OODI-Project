package com.hexagrammers.DamPlay.Models.Http;

import java.sql.Timestamp;

public class HttpNoticeBody {

    private String title;
    private String message;
    private String sender;

    HttpNoticeBody()
    {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getTime() {
        return new Timestamp(System.currentTimeMillis()).getTime();
    }

}
