package com.TMS.TASK_SERVICE.services;

import com.TMS.TASK_SERVICE.entities.Task;
import com.TMS.TASK_SERVICE.payload.Status;
import com.TMS.TASK_SERVICE.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepo taskRepo;

    public Task createTask(Task task,String role) throws Exception{
        if((!role.equals("ADMIN"))){
            throw new Exception("admin can create task");
        }else{
            task.setStatus(Status.PENDING);
            task.setCreatedAt(LocalDateTime.now());
            return taskRepo.save(task);
        }
    }

    @Override
    public Task getTaskById(String id)throws Exception {
        return taskRepo.findById(id).orElseThrow(()->new Exception("task not found"+ id));
    }

    @Override
    public void deleteTask(String taskid) throws Exception {
        taskRepo.deleteById(taskid);
    }

    @Override
    public List<Task> getAllTasks(Status status) {
        List<Task> tasks = taskRepo.findAll();
        return tasks.stream().filter(
                task -> status == null ||
                        task.getStatus().name().equalsIgnoreCase(status.toString())).collect(Collectors.toList());
    }

    @Override
    public Task updateTask(String taskid, String userid, Task updatedtask) throws Exception {
        Task task  = getTaskById(taskid);
        if(updatedtask.getStatus()!=null){
            task.setStatus(updatedtask.getStatus());
        }
        if(updatedtask.getCreatedAt()!=null){
            task.setCreatedAt(updatedtask.getCreatedAt());
        }
        if(updatedtask.getDeadlilne()!=null){
            task.setDeadlilne(updatedtask.getDeadlilne());
        }
        if(updatedtask.getDescription()!=null){
            task.setDescription(updatedtask.getDescription());
        }
        if(updatedtask.getTitle()!=null){
            task.setTitle(updatedtask.getTitle());
        }

        return taskRepo.save(task);
    }

    @Override
    public Task asigningToUser(String userid, String id) throws Exception {
        Task task = getTaskById(id);
        task.setAsigneduserid(userid);
        return taskRepo.save(task);
    }

    @Override
    public List<Task> tasksAsignedToUser(String userid, Status status) {
        List<Task> byAsignedUserId = taskRepo.findByAsigneduserid(userid);
        return byAsignedUserId.stream().filter(
                task->status==null || task.getStatus().name().equalsIgnoreCase(status.name())
                ).collect(Collectors.toList());
    }

    @Override
    public Task completeTask(String taskid) throws Exception {
        Task taskById = getTaskById(taskid);
        taskById.setStatus(Status.DONE);
        return taskRepo.save(taskById);
    }
}
