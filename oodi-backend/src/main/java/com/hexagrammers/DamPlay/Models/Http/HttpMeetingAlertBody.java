package com.hexagrammers.DamPlay.Models.Http;

import java.util.ArrayList;
import java.util.List;

public class HttpMeetingAlertBody {

    int sender;
    String title;
    String date;
    String meetingLink;
    List<Integer> attendees = new ArrayList<>();

    HttpMeetingAlertBody()
    {

    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public List<Integer> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Integer> attendees) {
        this.attendees = attendees;
    }
}
