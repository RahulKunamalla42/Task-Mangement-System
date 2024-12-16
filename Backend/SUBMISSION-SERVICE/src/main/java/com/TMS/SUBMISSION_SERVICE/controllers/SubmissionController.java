package com.TMS.SUBMISSION_SERVICE.controllers;

import com.TMS.SUBMISSION_SERVICE.services.SubmissonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubmissionController {

    @Autowired
    SubmissonService submissonService;
    @PostMapping("/submit")
    public ResponseEntity<?> submitTask(@RequestParam String userid,
                                 @RequestParam String taskid,
                                 @RequestParam String githublink) throws Exception {
        return new ResponseEntity<>( submissonService.submitTask(userid,taskid,githublink), HttpStatus.OK);
    }

    @GetMapping("/getSub")
    public ResponseEntity<?> getSubmissionbyid(@RequestParam String subid)throws Exception{
        return new ResponseEntity<>(submissonService.getSubmissionbyid(subid),HttpStatus.OK);
    }

    @GetMapping("/getallsubs")
    public ResponseEntity<?> getAllSubmissions()throws Exception{
        return new ResponseEntity<>(submissonService.getAllSubmissions(),HttpStatus.OK);
    }

    @GetMapping("/getsubsbytaskid")
    public ResponseEntity<?> getSubmissionsByTaskId(@RequestParam String taskid)throws Exception{
        return new ResponseEntity<>(submissonService.getSubmissionsByTaskId(taskid),HttpStatus.OK);
    }

    @PutMapping("/acordec")
    public ResponseEntity<?> acceptOrDeclineSubmisson(@RequestParam String taskid,
                                                      @RequestParam String status)throws Exception{
        return new ResponseEntity<>(submissonService.acceptOrDeclineSubmisson(taskid,status),HttpStatus.OK);
    }
}
