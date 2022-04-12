package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.MeetingAlert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingAlertRepository extends CrudRepository<MeetingAlert,Integer> {
    MeetingAlert findByTitle(String title);
}
