package com.hexagrammers.DamPlay.Controllers;

import com.hexagrammers.DamPlay.Models.Http.HttpNoticeBody;
import com.hexagrammers.DamPlay.Models.Notice;
import com.hexagrammers.DamPlay.Models.PrincipalUserDetails;
import com.hexagrammers.DamPlay.Models.User;
import com.hexagrammers.DamPlay.Services.NoticeManager;
import com.hexagrammers.DamPlay.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notice")
public class NoticeController {

    @Autowired
    NoticeManager noticeManager;

    @Autowired
    UserManager userManager;

    @PostMapping("")
    ResponseEntity<?> createNotice(@RequestBody HttpNoticeBody noticeHttpBody, Authentication authentication)
    {
        PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        Notice notice = new Notice(noticeHttpBody.getTitle(), noticeHttpBody.getMessage(), noticeHttpBody.getTime(), user);
        noticeManager.updateNotice(notice);
        user.addNotice(notice);
        userManager.updateUser(user);

        return new ResponseEntity<Notice>(notice, HttpStatus.CREATED);
    }
}
