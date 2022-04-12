package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.MeetingAlert;
import com.hexagrammers.DamPlay.Models.MeetingAttendee;
import com.hexagrammers.DamPlay.Repositories.MeetingAlertRepository;
import com.hexagrammers.DamPlay.Repositories.MeetingAttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingAlertManager {
    @Autowired
    MeetingAlertRepository meetingAlertRepository;
    @Autowired
    MeetingAttendeeRepository meetingAttendeeRepository;

    public void updateMeetingAlert(MeetingAlert meetingAlert)
    {
        meetingAlertRepository.save(meetingAlert);
    }

    public void saveMeetingAttendee(MeetingAttendee meetingAttendee)
    {
        meetingAttendeeRepository.save(meetingAttendee);
    }

    public MeetingAlert getMeetingAlert(String title)
    {
        return meetingAlertRepository.findByTitle(title);
    }

    public List<MeetingAlert> getMeetingAlerts()
    {
        return (List<MeetingAlert>) meetingAlertRepository.findAll();
    }


    public MeetingAlert getMeetingAlert(int id)
    {
        return meetingAlertRepository.findById(id).get();
    }

    public void deleteMeetingAlert(MeetingAlert meetingAlert)
    {
        meetingAlertRepository.delete(meetingAlert);
    }

    public void deleteMeetingAttendeesByMeetingAlert(MeetingAlert meetingAlert)
    {
        meetingAttendeeRepository.deleteMeetingAttendeesById(meetingAlert.getId());
    }
}
