package org.csu.workmaster_v1.web;


import io.swagger.annotations.ApiOperation;
import org.csu.workmaster_v1.Dao.MessageDao;
import org.csu.workmaster_v1.Dao.UserDao;
import org.csu.workmaster_v1.Entity.Message;
import org.csu.workmaster_v1.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Message")
public class MessageController {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private UserDao userDao;

    @PostMapping("/upload")
    @ApiOperation(value = "发布通知")
    public Map<String, Object> post(long groupId , String messagename, String messagecontent, String messageabstract, long messagepublisher) {
        Message message = new Message(groupId,messagename,messageabstract,messagecontent,messagepublisher);
        Map<String ,Object> map = new HashMap<String ,Object>();
        try {
            messageDao.saveMessage(message);
            map.put("status",1);
            map.put("message","success");
        }catch (Exception ex ){
            map.put("message","user has exist");
            map.put("status",0);
        }
        return map;
    }

    @GetMapping("/changestatus")
    public Map<String, Object> changestatus(String StudentId,long messageid){
        Map<String, Object> map = new HashMap<>();
        Message message = messageDao.findMessageByMessageid(messageid);
        try {
            message.setMessagestatus(1);
            messageDao.saveMessage(message);
            map.put("status",1);
            map.put("message","success");
        }catch (Exception ex ){
            map.put("message","Exception");
            map.put("status",0);
        }
        return map;
    }
    @GetMapping("/getmessages")
    //返回该用户所有的消息
    public Map<String, Object> getmessages(String StudentId){
        Map<String, Object> map = new HashMap<>();
        List<Message> list = messageDao.findMessageByStudentid(StudentId);
        try {
            map.put("status",1);
            map.put("message","success");
            map.put("messages",list);
        }catch (Exception ex ){
            map.put("message","Exception");
            map.put("status",0);
        }
        return map;
    }

}
