package org.csu.workmaster_v1.web;

import io.swagger.annotations.ApiOperation;
import org.csu.workmaster_v1.Dao.NotificationDao;
import org.csu.workmaster_v1.Dao.UserDao;
import org.csu.workmaster_v1.Entity.Message;
import org.csu.workmaster_v1.Entity.Notification;
import org.csu.workmaster_v1.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Notification")
public class NotificationController {
    @Autowired
    NotificationDao notificationDao;
    @Autowired
    UserDao userDao;

    @GetMapping("invitegroup")
    @ApiOperation(value = "邀请一人加入，需要双方学号，邀请内容")
    public Map<String ,Object> invite(long groupid, String studentid, String inviterstudentid,String notificationcontent){
        Map<String ,Object> map = new HashMap<>();
        try{
            User sender = userDao.findUserByStudentid(inviterstudentid);
            User receiver = userDao.findUserByStudentid(studentid);
            Notification notification = new Notification(sender.getId(),receiver.getId(),notificationcontent,groupid);
            notificationDao.saveNotification(notification);
            map.put("message","success");
            map.put("status",1);
        }catch (Exception ex){
            map.put("message","failed");
            map.put("status",0);
        }
        return map;
    }
    @GetMapping("/changestatus")
    public Map<String, Object> changestatus(String StudentId,long notificationid){
        Map<String, Object> map = new HashMap<>();
        Notification notification = notificationDao.findNotificationByNotificationId(notificationid);
        try {
            notification.setNotificationstatus(1);
            notificationDao.saveNotification(notification);
            map.put("status",1);
            map.put("message","success");
        }catch (Exception ex ){
            map.put("message","Exception");
            map.put("status",0);
        }
        return map;
    }
}
