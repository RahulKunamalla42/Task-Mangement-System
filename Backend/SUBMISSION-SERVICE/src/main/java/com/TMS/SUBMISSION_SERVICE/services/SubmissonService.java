package com.TMS.SUBMISSION_SERVICE.services;

import com.TMS.SUBMISSION_SERVICE.entities.Submission;

import java.util.List;

public interface SubmissonService {

    Submission submitTask(String userid,String taskid,String githublink)throws Exception;

    Submission getSubmissionbyid(String subid) throws Exception;

    List<Submission> getAllSubmissions();

    List<Submission> getSubmissionsByTaskId(String taskid);

    Submission acceptOrDeclineSubmisson(String taskid,String status)throws Exception;
}
