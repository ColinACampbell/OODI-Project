package com.hexagrammers.DamPlay.Models;

import javax.persistence.*;

@Entity
@Table(name = "meeting_attendees")
public class MeetingAttendee {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne()
    MeetingAlert meetingAlert;

    @ManyToOne()
    User user;

    public MeetingAttendee(MeetingAlert meetingAlert, User user)
    {
        this.meetingAlert = meetingAlert;
        this.user = user;
    }

    MeetingAttendee()
    {

    }

    public User getAttendee() {
        return user;
    }
}
