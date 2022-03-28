package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.Feedback;
import com.hexagrammers.DamPlay.Repositories.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class FeedbackManager {

    @Autowired
    FeedbackRepository feedbackRepository;
    public void updateFeedback(Feedback feedback)
    {

        feedbackRepository.save(feedback);
    }

    public Feedback getFeedback(int id)
    {

        return (Feedback) feedbackRepository.findById(id).get();
    }

    public List<Feedback> getFeedback()
    {

        return (List<Feedback>) feedbackRepository.findAll();
    }





}
