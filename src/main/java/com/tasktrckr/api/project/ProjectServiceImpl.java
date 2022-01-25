package com.tasktrckr.api.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tasktrckr.api.dto.project.ProjectResponseDto;
import com.tasktrckr.api.jpa.entities.ProjectEntity;
import com.tasktrckr.api.jpa.entities.TaskEntity;
import com.tasktrckr.api.jpa.repositories.ProjectRepository;
import com.tasktrckr.api.mapper.ProjectMapper;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public ProjectResponseDto getProject(Integer projectId) {
		ProjectEntity project = projectRepository.findById(projectId).orElse(null);
		if (project == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "project not found");
		}
		return projectMapper.toProjectResponseDto(project);
	}

}
