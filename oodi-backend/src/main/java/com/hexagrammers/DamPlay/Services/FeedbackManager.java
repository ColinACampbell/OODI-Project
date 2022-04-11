package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.Asset;
import com.hexagrammers.DamPlay.Models.Feedback;
import com.hexagrammers.DamPlay.Models.FeedbackReply;
import com.hexagrammers.DamPlay.Repositories.FeedbackReplyRepository;
import com.hexagrammers.DamPlay.Repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackManager {

    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    FeedbackReplyRepository feedbackReplyRepository;

    public void updateFeedback(Feedback feedback)
    {

        feedbackRepository.save(feedback);
    }

    public Feedback getFeedback(int id)
    {
        return (Feedback) feedbackRepository.findById(id).get();
    }

    public Feedback getFeedback(String title) {
        return feedbackRepository.findByTitle(title);
    }

    public List<Feedback> getFeedbacks(Asset asset)
    {

        return (List<Feedback>) feedbackRepository.findByAsset(asset);
    }


    public void deleteFeedback(int id)
    {
        feedbackRepository.deleteById(id);
    }

    public void deleteFeedbackReply(int id)
    {
        feedbackReplyRepository.deleteById(id);
    }

    public void saveReply(FeedbackReply feedbackReply)
    {
        feedbackReplyRepository.save(feedbackReply);
    }
}
