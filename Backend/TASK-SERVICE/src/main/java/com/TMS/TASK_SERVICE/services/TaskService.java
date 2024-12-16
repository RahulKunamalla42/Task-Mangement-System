package com.TMS.TASK_SERVICE.services;

import com.TMS.TASK_SERVICE.entities.Task;
import com.TMS.TASK_SERVICE.payload.Status;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {
    Task createTask(Task task,String role) throws Exception;
    Task getTaskById(String id) throws Exception;
    List<Task> getAllTasks(Status status);
    Task updateTask(String taskid,String userid,Task updatedtask) throws Exception;
    void deleteTask(String taskid) throws Exception;
    Task asigningToUser(String userid,String id) throws Exception;
    List<Task> tasksAsignedToUser(String userid,Status status);
    Task completeTask(String taskid) throws Exception;
}
