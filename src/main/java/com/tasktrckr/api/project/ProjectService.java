package com.tasktrckr.api.project;

import com.tasktrckr.api.dto.project.ProjectResponseDto;

public interface ProjectService {
	public ProjectResponseDto getProject(Integer projectId);
}
