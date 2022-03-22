package com.hexagrammers.DamPlay.Models;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "feedbacks")

public class Feedback {
    @Id
    @GeneratedValue
    private String title;
    private int date;
    private String body;
    private int id;




    public Feedback() { }


    public Feedback(String title,int date,String body,int id){

        this.title = title;
        this.date = date;
        this.body = body;
        this.id = id;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(int date) {
        this.date = date;
    }


    public String getBody(){ return body;}
    public String getTitle(){return title;}
    public int getDate(){return date;}

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
