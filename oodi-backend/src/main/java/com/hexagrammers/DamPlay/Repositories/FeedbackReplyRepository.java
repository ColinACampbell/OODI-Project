package com.hexagrammers.DamPlay.Repositories;

import com.hexagrammers.DamPlay.Models.FeedbackReply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackReplyRepository extends CrudRepository<FeedbackReply,Integer> {
}
