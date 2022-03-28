package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.*;
import com.hexagrammers.DamPlay.Models.Http.HttpFeedbackBody;
import com.hexagrammers.DamPlay.Services.FeedbackManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/feeback")
public class FeedbackController {

    @Autowired
    FeedbackManager feedbackManager;

    @GetMapping("")
    public List<Feedback> getFeedback()
    {

        return feedbackManager.getFeedback();
    }

    @GetMapping("{id}")
    public Feedback getFeedback(@PathVariable("id") int feedbackId)
    {

        return feedbackManager.getFeedback(feedbackId);
    }
    @PutMapping("{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable("id") int feedbackId, @RequestBody() Feedback feedbackBody)
    {
        Feedback feedback = feedbackManager.getFeedback(feedbackId);

        if (feedback == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // better response
        }

        feedback.setBody(feedbackBody.getBody());
        feedback.setTitle(feedbackBody.getTitle());



        feedbackManager.updateFeedback(feedback);

        return new ResponseEntity<>(feedback,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Feedback> createFeedback(@RequestBody HttpFeedbackBody feedbackBody)
    {

        Feedback feedback = new Feedback(feedbackBody.getTitle(),feedbackBody.getBody());


        feedbackManager.updateFeedback(feedback);



        return new ResponseEntity<Feedback>(feedback, HttpStatus.CREATED);
    }




}
