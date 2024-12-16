package com.TMS.TASK_SERVICE.repos;

import com.TMS.TASK_SERVICE.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,String> {
    List<Task> findByAsigneduserid(String id);
}
