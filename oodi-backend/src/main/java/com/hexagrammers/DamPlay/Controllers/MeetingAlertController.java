package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.Http.HttpMeetingAlertBody;
import com.hexagrammers.DamPlay.Models.MeetingAlert;
import com.hexagrammers.DamPlay.Models.MeetingAttendee;
import com.hexagrammers.DamPlay.Models.PrincipalUserDetails;
import com.hexagrammers.DamPlay.Models.User;
import com.hexagrammers.DamPlay.Services.MeetingAlertManager;
import com.hexagrammers.DamPlay.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meeting-alert")
public class MeetingAlertController {

    @Autowired
    MeetingAlertManager meetingAlertManager;

    @Autowired
    UserManager userManager;

    @PostMapping("")
    public ResponseEntity<MeetingAlert> createMeetingAlert(@RequestBody HttpMeetingAlertBody meetingAlertBody, Authentication authentication)
    {
        PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();
        MeetingAlert meetingAlert = new MeetingAlert(meetingAlertBody.getTitle(), user,meetingAlertBody.getDate(),meetingAlertBody.getMeetingLink());

        meetingAlertManager.updateMeetingAlert(meetingAlert);

        user.addMeetingAlerts(meetingAlert);

        for (int userID : meetingAlertBody.getAttendees())
        {
            User attendee = userManager.findById(userID);
            if (attendee == null)
                continue;

            MeetingAttendee meetingAttendee = new MeetingAttendee(meetingAlert,attendee);
            meetingAlertManager.saveMeetingAttendee(meetingAttendee);

            meetingAlert.addMeetingAttendee(meetingAttendee);
            attendee.addMeetingAttendee(meetingAttendee); // TODO Check this
            userManager.updateUser(attendee);

        }

        meetingAlertManager.updateMeetingAlert(meetingAlert);

        return new ResponseEntity<>(meetingAlert, HttpStatus.CREATED);

    }

    @GetMapping("")
    public List<MeetingAlert> getMeetingAlerts()
    {
        return meetingAlertManager.getMeetingAlerts();
    }

    @GetMapping("{id}")
    public MeetingAlert getMeetingAlert(@PathVariable("id") int id)
    {
        if (meetingAlertManager.getMeetingAlert(id) == null)
            return null;
        return meetingAlertManager.getMeetingAlert(id);
    }

    @PutMapping("{id}")
    public MeetingAlert editMeetingAlert(@PathVariable("id") int id, @RequestBody HttpMeetingAlertBody meetingAlertBody)
    {
        if (meetingAlertManager.getMeetingAlert(id) == null)
            return null;

        MeetingAlert meetingAlert = meetingAlertManager.getMeetingAlert(id);
        meetingAlert.setTitle(meetingAlertBody.getTitle());
        meetingAlert.setMeetingLink(meetingAlertBody.getMeetingLink());
        meetingAlert.setDate(meetingAlertBody.getDate());


        meetingAlertManager.deleteMeetingAttendeesByMeetingAlert(meetingAlert);
        for (int userID : meetingAlertBody.getAttendees())
        {
            User attendee = userManager.findById(userID);
            if (attendee == null)
                continue;

            MeetingAttendee meetingAttendee = new MeetingAttendee(meetingAlert,attendee);
            meetingAlertManager.saveMeetingAttendee(meetingAttendee);

            meetingAlert.addMeetingAttendee(meetingAttendee);
            attendee.addMeetingAttendee(meetingAttendee); // TODO Check this
            userManager.updateUser(attendee);

        }

        meetingAlertManager.updateMeetingAlert(meetingAlert);
        return meetingAlert;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMeetingAlert(@PathVariable("id") int id)
    {
        MeetingAlert meetingAlert = meetingAlertManager.getMeetingAlert(id);
        meetingAlertManager.deleteMeetingAttendeesByMeetingAlert(meetingAlert);
        meetingAlertManager.deleteMeetingAlert(meetingAlert);

        return new ResponseEntity<>(meetingAlertManager.getMeetingAlerts(),HttpStatus.OK);
    }
}
