package com.TMS.TASK_SERVICE.controller;

import com.TMS.TASK_SERVICE.entities.Task;
import com.TMS.TASK_SERVICE.entities.dtos.UserDto;
import com.TMS.TASK_SERVICE.payload.AuthResponce;
import com.TMS.TASK_SERVICE.payload.Status;
import com.TMS.TASK_SERVICE.services.TaskServiceImpl;
import com.TMS.TASK_SERVICE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;
    @Autowired
    UserService userService;

    @GetMapping("/gettask/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable String id) throws Exception {
        Task task = taskService.getTaskById(id);
        return task==null?
                new ResponseEntity<>(new AuthResponce("task not found"), HttpStatus.NOT_FOUND):
                new ResponseEntity<>(task,HttpStatus.OK);
    }
    @PostMapping("/create")
    public  ResponseEntity<?> createTask(@RequestBody Task task,
                                         @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getProfile(jwt);
        String role = user.getRole();
        Task createdTask = taskService.createTask(task, role);
        return new ResponseEntity<>(createdTask,HttpStatus.CREATED);
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAllTasks(@RequestParam(required = false)Status status){
        List<Task> allTasks = taskService.getAllTasks(status);
        return new ResponseEntity<>(allTasks,HttpStatus.OK);
    }
    @DeleteMapping("/deletetask")
    public ResponseEntity<?> deleteTask(@RequestParam String id) throws Exception {
        taskService.deleteTask(id);
        return new ResponseEntity<>(new AuthResponce("task deleted"+id),HttpStatus.OK);
    }

    @PutMapping("/updatetask")
    public ResponseEntity<?> updateTask(@RequestBody Task task,
                                        @RequestParam(required = false) String userid,
                                        @RequestParam String taskid) throws Exception {
        return new ResponseEntity<>(taskService.updateTask(taskid,userid,task),HttpStatus.OK);
    }
    @PutMapping("/asigntouser")
    public ResponseEntity<?> asigntouser(@RequestParam String userid,
                                        @RequestParam String taskid) throws Exception {
        return new ResponseEntity<>(taskService.asigningToUser(userid,taskid),HttpStatus.OK);
    }

    @PutMapping("/completetask")
    public ResponseEntity<?> completetask(@RequestParam String taskid) throws Exception {
        return new ResponseEntity<>(taskService.completeTask(taskid),HttpStatus.OK);
    }

    @GetMapping("/tasksasigndtouser")
    public ResponseEntity<?> tasksAsignedToUser(@RequestParam String userid,@RequestParam(required = false) Status status){
        return new ResponseEntity<>(taskService.tasksAsignedToUser(userid,status),HttpStatus.OK);
    }

    @GetMapping("/home")
    public String home(){
        return "hello";
    }
}
