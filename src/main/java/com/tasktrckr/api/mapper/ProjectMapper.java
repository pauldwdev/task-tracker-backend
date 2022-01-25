package com.tasktrckr.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.tasktrckr.api.dto.project.ProjectResponseDto;
import com.tasktrckr.api.jpa.entities.ProjectEntity;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
	
	public ProjectResponseDto toProjectResponseDto(ProjectEntity projectEntity);

	public List<ProjectResponseDto> toProjectResponseDtoList(List<ProjectEntity> projectEntity);
}
