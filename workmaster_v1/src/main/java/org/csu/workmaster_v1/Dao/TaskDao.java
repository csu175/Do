package org.csu.workmaster_v1.Dao;

import org.csu.workmaster_v1.Entity.Task;

import java.util.List;

public interface TaskDao {

    public void saveTask(Task task);
    public Task findTaskByTaskName(String TaskName);
    public void updateTask(Task task);
    public void deleteTaskById(Long id);
    public List<Task> findMessageByStudentid(String studentId);
}
