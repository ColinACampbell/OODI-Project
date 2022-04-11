package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.*;
import com.hexagrammers.DamPlay.Models.Http.HttpFeedbackBody;
import com.hexagrammers.DamPlay.Models.Http.HttpFeedbackReply;
import com.hexagrammers.DamPlay.Services.AssetManager;
import com.hexagrammers.DamPlay.Services.FeedbackManager;
import com.hexagrammers.DamPlay.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    FeedbackManager feedbackManager;

    @Autowired
    AssetManager assetManager;


    @Autowired
    UserManager userManager;

    @PostMapping("")
    public ResponseEntity<Feedback> createFeedback(@RequestBody HttpFeedbackBody feedbackBody, Authentication authentication)
    {

        PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();

        if (feedbackManager.getFeedback(feedbackBody.getTitle()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        User user = userDetails.getUser();

        Feedback feedback = new Feedback(feedbackBody.getTitle(),feedbackBody.getBody(),user);

        // Get the asset from the asset id using the asset manager
        Asset asset = assetManager.getAsset(feedbackBody.getAssetID());
        feedback.setAsset(asset);

        feedbackManager.updateFeedback(feedback);

        asset.addFeedbackReply(feedback);

        assetManager.updateAsset(asset);

        user.addFeedback(feedback);
        userManager.updateUser(user);

        return new ResponseEntity<Feedback>(feedback, HttpStatus.CREATED);
    }

    @GetMapping("{assetID}")
    public List<Feedback> getFeedbacks(@PathVariable("assetID") int assetID)
    {
        Asset asset = assetManager.getAsset(assetID);
        return feedbackManager.getFeedbacks(asset);
    }

    // TODO : Review
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

    @PostMapping("/reply")
    public ResponseEntity<FeedbackReply> createFeedbackReply(@RequestBody HttpFeedbackReply httpFeedbackReplyBody, Authentication authentication)
    {
        if (feedbackManager.getFeedbackReply(httpFeedbackReplyBody.getTitle()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        Feedback feedback = feedbackManager.getFeedback(httpFeedbackReplyBody.getFeedbackID());

        if (feedback == null)
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);


        FeedbackReply feedbackReply = new FeedbackReply(httpFeedbackReplyBody.getTitle(),httpFeedbackReplyBody.getBody(),user);
        feedback.addFeedbackReply(feedbackReply);
        feedbackReply.setFeedback(feedback);

        feedbackManager.updateFeedback(feedback);
        feedbackManager.saveReply(feedbackReply);

        user.addFeedbackReply(feedbackReply);
        userManager.updateUser(user);

        return new ResponseEntity<>(feedbackReply,HttpStatus.CREATED);
    }

}
