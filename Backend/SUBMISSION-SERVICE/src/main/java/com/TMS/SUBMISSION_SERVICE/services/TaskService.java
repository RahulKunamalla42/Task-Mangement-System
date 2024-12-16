package com.TMS.SUBMISSION_SERVICE.services;

import com.TMS.SUBMISSION_SERVICE.dtos.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="TASK-SERVICE",url = "http://localhost:5002")
public interface TaskService {

    @GetMapping("/api/gettask/{id}")
    TaskDto getTaskById(@PathVariable String id) throws Exception;

    @PutMapping("/api/completetask")
    TaskDto completetask(@RequestParam String taskid) throws Exception;
}
