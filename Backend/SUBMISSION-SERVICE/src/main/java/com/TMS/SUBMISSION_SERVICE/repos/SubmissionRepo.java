package com.TMS.SUBMISSION_SERVICE.repos;

import com.TMS.SUBMISSION_SERVICE.entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepo extends JpaRepository<Submission,String> {

    List<Submission> findByTaskid(String taskid);
}
