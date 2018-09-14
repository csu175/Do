package org.csu.workmaster_v1.web;

import io.swagger.annotations.ApiOperation;
import org.csu.workmaster_v1.Dao.TaskDao;
import org.csu.workmaster_v1.Entity.Message;
import org.csu.workmaster_v1.Entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Message")
public class TaskController {

    @Autowired
    private TaskDao taskdao;

    @PostMapping("/upload")
    @ApiOperation(value = "发布任务")
    public Map<String, Object> post(long groupId , String taskename, String taskcontent,long taskpublisher, String fileformat, long deadline) {
        Map<String ,Object> map = new HashMap<String ,Object>();
        try {
            Task task = new Task(groupId,taskename,taskcontent,taskpublisher,fileformat,deadline);
            taskdao.saveTask(task);
            map.put("status",1);
            map.put("message","success");
        }catch (Exception ex ){
            map.put("message","user has exist");
            map.put("status",0);
        }
        return map;
    }
    @GetMapping("/gettasks")
    //返回该用户所有的消息
    public Map<String, Object> gettasks(String StudentId){
        Map<String, Object> map = new HashMap<>();
        List<Task> list = taskdao.findMessageByStudentid(StudentId);
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