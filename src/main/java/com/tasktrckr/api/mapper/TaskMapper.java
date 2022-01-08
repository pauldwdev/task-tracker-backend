package com.tasktrckr.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tasktrckr.api.dto.task.TaskRequestDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasttrckr.api.jpa.entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	public TaskResponseDto toTaskResponseDto(TaskEntity taskEntity);

	public List<TaskResponseDto> toTaskResponseDtoList(List<TaskEntity> taskEntity);
	
	@Mapping(source = "projectId", target = "projectEntity.projectId")
	public TaskEntity toTaskEntity(TaskRequestDto taskRequestDto);
}
