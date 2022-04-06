package com.hexagrammers.DamPlay.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meeting_alerts")
public class MeetingAlert {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String date;
    private String meetingLink;

    @OneToMany(mappedBy = "meetingAlert")
    private List<MeetingAttendee> attendees = new ArrayList<>();


    @ManyToOne()
    User sender;

    MeetingAlert()
    {

    }

    public MeetingAlert(String title, User sender, String date, String meetingLink) {
        this.title = title;
        this.sender = sender;
        this.date = date;
        this.meetingLink = meetingLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getSender() {
        return sender;
    }

    public List<MeetingAttendee> getAttendees() {
        return attendees;
    }

    public void addMeetingAttendee(MeetingAttendee meetingAttendee)
    {
        this.attendees.add(meetingAttendee);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }
}
