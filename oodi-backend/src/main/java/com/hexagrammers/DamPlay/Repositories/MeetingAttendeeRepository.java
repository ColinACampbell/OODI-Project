package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.MeetingAttendee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MeetingAttendeeRepository extends CrudRepository<MeetingAttendee,Integer> {
    @Modifying()
    @Transactional()
    @Query(value = "delete from meeting_attendees where meeting_alert_id = ?1",nativeQuery = true)
    void deleteMeetingAttendeesById(int meetingAlertId);
}
