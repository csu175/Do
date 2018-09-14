package org.csu.workmaster_v1.web;


import io.swagger.annotations.Api;
import org.csu.workmaster_v1.Dao.UserDao;
import org.csu.workmaster_v1.Entity.File;
import org.csu.workmaster_v1.Entity.Group;
import org.csu.workmaster_v1.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
@Api(tags = "1.1", description = "群组管理", value = "群组管理")
public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(GroupController.class);
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private FileDao filedao;


    @PostMapping("/creat")
    public Map<String ,Object> creat(String studentId,String groupname,String description,long Avatar){
        Map<String ,Object> map = new HashMap<>();
        User user = userDao.findUserByStudentid(studentId);
        Group group = new Group(groupname,description,Avatar);
        user.getGroupList().add(group.getGroupid());
        group.getUserlist().add(user.getId());
        return map;
    }
    @GetMapping("/adduser")
    public Map<String, Object> adduser(String studentId, String groupName){
        Map<String ,Object> map = new HashMap<>();
        try {
            User user = userDao.findUserByStudentid(studentId);
            Group group = groupDao.finGroupByGroupName(groupName);
            if( user != null && group != null ) {
                user.getGroupList().add(group.getGroupid());
                group.getUserlist().add(user.getId());
                map.put("status",1);
                map.put("message","success");
                userDao.updateUser(user);
                groupDao.updateGroup(group);
            }else{
                map.put("status",0);
                map.put("message","failed");
            }
        }catch (Exception ex){
            map.put("message","there is an Exception in the server");
        }
        return  map;
    }
}
