package org.csu.workmaster_v1.web;

import com.battcn.swagger.properties.ApiDataType;
import com.battcn.swagger.properties.ApiParamType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.csu.workmaster_v1.Dao.UserDao;
import org.csu.workmaster_v1.Entity.Message;
import org.csu.workmaster_v1.Entity.Notificaion;
import org.csu.workmaster_v1.Entity.Task;
import org.csu.workmaster_v1.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.PastOrPresent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private org.csu.workmaster_v1.service.MailService mailService;


    @GetMapping("/login")
    @ApiOperation(value = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY),
            @ApiImplicitParam(name = "userpassword", value = "密码", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY),
    })
    public Map<String,Object> login(String username, String userpassword ,HttpServletRequest request) {
        User user = userDao.findUserByusername(username);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            if(user.getUserpassword().equals(userpassword)) {
                if(user.getVerification_status()==0){
                    map.put("status",0);
                    map.put("message", "please verify the mail");
                }else{
                    map.put("status", 1);
                    map.put("message", "success");
                    map.put("user",user);
                    request.getSession().setAttribute("studentid", user.getStudentId());//设置session
                }
            }else {
                map.put("status",0);
                map.put("message", "password invalid");
            }
        }else {
                map.put("status",0);
                map.put("message", "user don't exit");
        }
        return map;
    }

    @PostMapping("/registe")
    @ApiOperation(value = "注册")
    public Map<String, Object> post(String studentId, String userpassword, String username, HttpServletRequest request) {
        User user = userDao.findUserByStudentid(studentId);
        Map<String, Object> map = new HashMap<>();
        if(user !=null){
            map.put("message","user has exist");
            map.put("status",0);
        }else{
            map.put("status",1);
            map.put("message","success");
            userDao.saveUser(new User(studentId,userpassword,username));
            request.getSession().setAttribute("studentid",user.getStudentId());
            //发送邮件
            mailService.sendLoginMail(user.getStudentId());
        }
        return map;
    }

    @PostMapping("registe/{id}")
    @ApiOperation(value = "修改用户验证状态")
    public void put(@PathVariable String id ) {
        User user = userDao.findUserByStudentid(id);
        user.setVerification_status(1);
        userDao.updateUser(user);
    }

    @GetMapping("/getUser")
    public Map<String, Object> getUser(String StudentId){
        Map<String, Object> map = new HashMap<>();
        User user = userDao.findUserByStudentid(StudentId);
        List<Message> messagelist = MessageDao.findMessageByStudentid(StudentId);
        List<Task> tasklist = TaskDao.findMessageByStudentid(StudentId);
        List<Notificaion> notificationlist = NotificationDao.findMessageByStudentid(StudentId);

        if(user !=null){
            map.put("user",user);
            map.put("messagelist",messagelist);
            map.put("tasklist",tasklist);
            map.put("notificationlist",notificationlist);
            map.put("status",1);
            map.put("message","success");
        }else{
            map.put("status",0);
            map.put("message","user don't exist");
        }
        return map;
    }
    @PostMapping("/changepsw")
    public Map<String,Object> changePsw(String StudenId,String psw){
        Map<String, Object> map = new HashMap<>();
        User user = userDao.findUserByStudentid(StudenId);
        user.setUserpassword(psw);
        userDao.updateUser(user);
        return map;
    }
    //------头像暂放-----//
    @PostMapping("/changeAvatar")
    public Map<String,Object> changeAvator(String StudenId,long Avatar){
        Map<String, Object> map = new HashMap<>();
        try{
            User user = userDao.findUserByStudentid(StudenId);
            if( Avatar>0 && user!=null){
                user.setAvatar(Avatar);
                userDao.updateUser(user);
                map.put("status",1);
                map.put("message","change the avatar successfully");
        }catch(Exception ex){
            ex.print
        }
        return map;
    }
}