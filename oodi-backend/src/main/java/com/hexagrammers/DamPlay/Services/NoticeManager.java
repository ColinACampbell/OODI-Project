package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.Notice;
import com.hexagrammers.DamPlay.Repositories.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeManager {

    @Autowired
    NoticeRepository noticeRepository;


    public void updateNotice(Notice notice)
    {
        noticeRepository.save(notice);
    }

    public Notice getNotice(int id)
    {
        return  noticeRepository.findById(id).get();
    }

    /* Get All Notice Method*/
    public List<Notice> getAllNotices(){
        return (List<Notice>) noticeRepository.findAll();
    }

}
