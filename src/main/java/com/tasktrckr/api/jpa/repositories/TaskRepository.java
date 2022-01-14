package com.tasktrckr.api.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasktrckr.api.jpa.entities.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer>{

}
