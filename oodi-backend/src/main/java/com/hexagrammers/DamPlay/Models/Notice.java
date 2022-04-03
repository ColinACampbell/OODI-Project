package com.hexagrammers.DamPlay.Models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notices")
public class Notice {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;
    private String message;
    private String sender;
    private Long time;

    @OneToMany(mappedBy = "notices")

    private List<User> notices;
    @ManyToOne()
    User user;



    public Notice() {
        this.notice = new ArrayList<>();
    }

    public Notice(String title, String message, String sender, Long time, User user){
        this.title = title;
        this.message = message;
        this.sender = sender;
        this.time = time;
        this.user = user;
        this.notice = new ArrayList<>();



    }

    public List<User> getNotices() {
        return notices;
    }

    public void setNotices(List<User> notices) {
        this.notices = notices;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User creator) {
        this.user = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
