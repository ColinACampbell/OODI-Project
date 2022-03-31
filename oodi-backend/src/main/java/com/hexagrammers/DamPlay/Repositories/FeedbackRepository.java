package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.Feedback;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback,Integer> {
    List<Feedback> findByAsset(Asset asset);
}
