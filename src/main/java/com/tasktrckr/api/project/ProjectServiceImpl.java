package com.tasktrckr.api.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tasktrckr.api.dto.project.ProjectRequestDto;
import com.tasktrckr.api.dto.project.ProjectResponseDto;
import com.tasktrckr.api.dto.task.TaskRequestDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;
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

	@Override
	public List<ProjectResponseDto> getProjects() {
		return projectMapper.toProjectResponseDtoList(projectRepository.findAll());

	}

	@Override
	public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto) {
		if (projectRepository.existsById(projectRequestDto.getProjectId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create new project with existing id.");
		}
		return projectMapper
				.toProjectResponseDto(projectRepository.saveAndFlush(projectMapper.toProjectEntity(projectRequestDto)));
	}

	@Override
	public ProjectResponseDto updateProject(ProjectRequestDto projectRequestDto) {
		if (!projectRepository.existsById(projectRequestDto.getProjectId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Cannot update project. Project with provided ID does not exist.");
		}
		return projectMapper
				.toProjectResponseDto(projectRepository.saveAndFlush(projectMapper.toProjectEntity(projectRequestDto)));
	}

	@Override
	public void deleteProject(Integer projectId) {
		if (!projectRepository.existsById(projectId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Cannot delete project. Project with provided ID does not exist.");
		}
		projectRepository.deleteById(projectId);
	}

}
