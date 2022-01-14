package com.tasktrckr.api.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tasktrckr.api.jpa.entities.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

}
