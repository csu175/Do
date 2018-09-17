package org.csu.workmaster_v1.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task {
    public long taskid;
    public long groupid;
    public String taskname;
    public String taskcontent;
    public long time;
    public long deadline;
    public long taskpublisher;
    public List userdone = new ArrayList<Long>();
    public String fileformat;
    public List filelist = new ArrayList<Long>();
    public int taskstatus;

    public Task(long groupid, String taskname, String taskcontent, long taskpublisher, String fileformat,long deadline) {
        this.taskid = new Random().nextLong();
        this.groupid = groupid;
        this.taskname = taskname;
        this.taskcontent = taskcontent;
        this.time = System.currentTimeMillis();
        this.taskpublisher = taskpublisher;
        this.fileformat = fileformat;
        this.taskstatus = 0;
    }

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskcontent() {
        return taskcontent;
    }

    public void setTaskcontent(String taskcontent) {
        this.taskcontent = taskcontent;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTaskpublisher() {
        return taskpublisher;
    }

    public void setTaskpublisher(long taskpublisher) {
        this.taskpublisher = taskpublisher;
    }

    public List getUserdone() {
        return userdone;
    }

    public void setUserdone(List userdone) {
        this.userdone = userdone;
    }

    public String getFileformat() {
        return fileformat;
    }

    public void setFileformat(String fileformat) {
        this.fileformat = fileformat;
    }

    public long getDeadline() { return deadline;}

    public void setDeadline(long deadline) {  this.deadline = deadline;}

    public List getFilelist() { return filelist;}

    public void setFilelist(List filelist) { this.filelist = filelist;}

    public int getTaskstatus() { return taskstatus;}

    public void setTaskstatus(int taskstatus) { this.taskstatus = taskstatus; }
}

