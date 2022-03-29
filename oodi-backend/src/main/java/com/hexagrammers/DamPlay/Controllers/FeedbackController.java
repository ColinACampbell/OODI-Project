package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.*;
import com.hexagrammers.DamPlay.Models.Http.HttpFeedbackBody;
import com.hexagrammers.DamPlay.Services.AssetManager;
import com.hexagrammers.DamPlay.Services.FeedbackManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    FeedbackManager feedbackManager;

    @Autowired
    AssetManager assetManager;

    @PostMapping("")
    public ResponseEntity<Feedback> createFeedback(@RequestBody HttpFeedbackBody feedbackBody)
    {
        // Remove the placeholder asset and pass the correct asset using
        Feedback feedback = new Feedback(feedbackBody.getTitle(),feedbackBody.getBody(),feedbackBody.getDate());

        // Get the asset from the asset id using the asset manager
        Asset asset = assetManager.getAsset(feedbackBody.getAssetID());
        feedback.setAsset(asset);

        feedbackManager.updateFeedback(feedback);

        asset.addFeedbackReply(feedback);

        assetManager.updateAsset(asset);

        return new ResponseEntity<Feedback>(feedback, HttpStatus.CREATED);
    }

    @GetMapping("{assetID}")
    public List<Feedback> getFeedbacks(@PathVariable("assetID") int assetID)
    {
        // Write code to get all the feedbacks for an asset
        return feedbackManager.getFeedback();
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

}
