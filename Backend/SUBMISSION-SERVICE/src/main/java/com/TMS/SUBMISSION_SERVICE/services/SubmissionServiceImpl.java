package com.TMS.SUBMISSION_SERVICE.services;

import com.TMS.SUBMISSION_SERVICE.dtos.TaskDto;
import com.TMS.SUBMISSION_SERVICE.entities.Submission;
import com.TMS.SUBMISSION_SERVICE.repos.SubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissonService{

    @Autowired
    SubmissionRepo submissionRepo;
    @Autowired
    TaskService taskService;

    @Override
    public Submission submitTask(String userid, String taskid, String githublink) throws Exception {
        TaskDto task = taskService.getTaskById(taskid);
        if(task!=null){
            Submission sub=new Submission();
            sub.setGithublink(githublink);
            sub.setTaskid(taskid);
            sub.setUserid(userid);
            sub.setSubmissiontime(LocalDateTime.now());
            return submissionRepo.save(sub);
        }else{
            throw new Exception("TASK NOT EXITS"+ userid);
        }

    }

    @Override
    public Submission getSubmissionbyid(String subid) throws Exception {
        return submissionRepo.findById(subid).orElseThrow(()->new Exception("SUBMISSION NOT FOUND"+subid));
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return submissionRepo.findAll();
    }

    @Override
    public List<Submission> getSubmissionsByTaskId(String taskid) {
        return submissionRepo.findByTaskid(taskid);
    }

    @Override
    public Submission acceptOrDeclineSubmisson(String taskid, String status) throws Exception {
        List<Submission> submissionsByTaskId = getSubmissionsByTaskId(taskid);
        if (submissionsByTaskId.isEmpty()) {
            throw new Exception("No submissions found for task ID: " + taskid);
        }
        // Sort by submission time and get the latest submission
        submissionsByTaskId.sort((s1, s2) -> s2.getSubmissiontime().compareTo(s1.getSubmissiontime()));
        Submission latestSubmission = submissionsByTaskId.get(0); // Get the most recent submission

        // Update the status of the latest submission
        latestSubmission.setStatus(status);

        // Complete the task using TaskService
        taskService.completetask(latestSubmission.getTaskid());

        // Save and return the updated submission
        return submissionRepo.save(latestSubmission);
    }

}
