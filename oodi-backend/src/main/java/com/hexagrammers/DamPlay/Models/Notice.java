package com.hexagrammers.DamPlay.Models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notices")
public class Notice {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private int id;

    private String title;
    private String message;
    private Long time;

    @ManyToOne()
    User user;

    public Notice() {

    }

    public Notice(String title, String message, Long time, User user){
        this.title = title;
        this.message = message;
        this.time = time;
        this.user = user;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return user;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


    public void setUser(User creator) {
        this.user = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
