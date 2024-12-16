package com.TMS.SUBMISSION_SERVICE.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userid;
    private String taskid;
    private String githublink;
    private String status="PENDING";
    private LocalDateTime submissiontime;

    public Submission() {
    }

    public Submission(String id, String userid, String taskid, String githublink, String status, LocalDateTime submissiontime) {
        this.id = id;
        this.userid = userid;
        this.taskid = taskid;
        this.githublink = githublink;
        this.status = status;
        this.submissiontime = submissiontime;
    }

    public String getGithublink() {
        return githublink;
    }

    public void setGithublink(String githublink) {
        this.githublink = githublink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmissiontime() {
        return submissiontime;
    }

    public void setSubmissiontime(LocalDateTime submissiontime) {
        this.submissiontime = submissiontime;
    }
}
