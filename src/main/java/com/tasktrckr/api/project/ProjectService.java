package com.tasktrckr.api.project;

import java.util.List;

import com.tasktrckr.api.dto.project.ProjectRequestDto;
import com.tasktrckr.api.dto.project.ProjectResponseDto;

public interface ProjectService {
	public ProjectResponseDto getProject(Integer projectId);

	public List<ProjectResponseDto> getProjects();

	public ProjectResponseDto createProject(ProjectRequestDto projectRequestDto);

	public ProjectResponseDto updateProject(ProjectRequestDto projectRequestDto);

	void deleteProject(Integer projectId);
}
