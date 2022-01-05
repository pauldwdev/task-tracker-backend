package com.tasktrckr.api.mapper;

import org.mapstruct.Mapper;

import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasttrckr.api.jpa.entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	public TaskResponseDto toTaskResponseDto(TaskEntity taskEntity);
}
