package com.hexagrammers.DamPlay.Models;



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


    public Feedback(String title,String body){

        this.title = title;
        this.body = body;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }



    public String getBody(){ return body;}
    public String getTitle(){return title;}

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
