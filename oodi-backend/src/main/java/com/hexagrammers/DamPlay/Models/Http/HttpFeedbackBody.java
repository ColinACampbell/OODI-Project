package com.hexagrammers.DamPlay.Models.Http;

import com.hexagrammers.DamPlay.Models.Feedback;

import java.util.List;

public class HttpFeedbackBody extends Feedback {
    private List<Integer> feedbackList;


    public HttpFeedbackBody(String title,String body,int id)
    {
        super(title, body);
    }


}
