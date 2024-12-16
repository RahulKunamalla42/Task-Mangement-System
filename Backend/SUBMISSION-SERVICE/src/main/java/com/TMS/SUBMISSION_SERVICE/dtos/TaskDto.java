package com.TMS.SUBMISSION_SERVICE.dtos;

import java.time.LocalDateTime;

public class TaskDto {

    private String id;
    private String title;
    private String description;
    private String deadlilne;
    private LocalDateTime createdAt;
    private String asigneduserid;
    private Status status;

    public TaskDto() {
    }

    public TaskDto(String id, String title, String description, String deadlilne, LocalDateTime createdAt, String asigneduserid, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadlilne = deadlilne;
        this.createdAt = createdAt;
        this.asigneduserid = asigneduserid;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadlilne() {
        return deadlilne;
    }

    public void setDeadlilne(String deadlilne) {
        this.deadlilne = deadlilne;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAsigneduserid() {
        return asigneduserid;
    }

    public void setAsigneduserid(String asigneduserid) {
        this.asigneduserid = asigneduserid;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
