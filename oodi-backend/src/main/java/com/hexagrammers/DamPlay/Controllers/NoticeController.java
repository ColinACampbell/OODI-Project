package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.Http.HttpNoticeBody;

import java.util.List;

import com.hexagrammers.DamPlay.Models.Notice;
import com.hexagrammers.DamPlay.Models.PrincipalUserDetails;
import com.hexagrammers.DamPlay.Models.User;
import com.hexagrammers.DamPlay.Services.NoticeManager;
import com.hexagrammers.DamPlay.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    NoticeManager noticeManager;

    @Autowired
    UserManager userManager;

    @PostMapping("")
    ResponseEntity<?> createNotice(@RequestBody HttpNoticeBody noticeHttpBody, Authentication authentication)
    {

        if (noticeManager.getNotice(noticeHttpBody.getTitle()) != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        Notice notice = new Notice(noticeHttpBody.getTitle(), noticeHttpBody.getMessage(), noticeHttpBody.getTime(), user);
        noticeManager.updateNotice(notice);
        user.addNotice(notice);
        userManager.updateUser(user);

        return new ResponseEntity<Notice>(notice, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity<?> updateNotice(@PathVariable("id") int noticeID, @RequestBody HttpNoticeBody noticeHttpBody)
    {
        Notice notice = noticeManager.getNotice(noticeID);

        notice.setTitle(noticeHttpBody.getTitle());
        notice.setMessage(noticeHttpBody.getMessage());
        noticeManager.updateNotice(notice);

        return new ResponseEntity<Notice>(notice, HttpStatus.OK);
    }

    /* Get All Notice Method*/
    @GetMapping("")
    public List<Notice> getAllNotices()
    {
        return noticeManager.getAllNotices();
    }

    @GetMapping("{id}")
    public Notice getNotice(@PathVariable("id") int id)
    {
        return noticeManager.getNotice(id);
    }
}
