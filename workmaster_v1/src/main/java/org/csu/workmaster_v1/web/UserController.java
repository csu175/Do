package org.csu.workmaster_v1.web;

import com.battcn.swagger.properties.ApiDataType;
import com.battcn.swagger.properties.ApiParamType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.csu.workmaster_v1.Dao.UserDao;
import org.csu.workmaster_v1.Entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserDao userDao;

    @GetMapping("/login")
    @ApiOperation(value = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY),
            @ApiImplicitParam(name = "userpassword", value = "密码", dataType = ApiDataType.STRING, paramType = ApiParamType.QUERY),
    })
    public Map<String,Object> login(String username, String userpassword) {
        User user = userDao.findUserByusername(username);
        Map<String, Object> map = new HashMap<>();
        if(user!=null){
            if(user.getUserpassword().equals(userpassword)) {
                map.put("status", 1);
                map.put("message", "success");
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
    public User post(String studentId, String userpassword,String username) {
        return new User(studentId,userpassword,username);
    }
}