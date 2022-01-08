package com.tasktrckr.api.task;

import java.util.List;

import com.tasktrckr.api.dto.task.TaskRequestDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;

public interface TaskService {
	public TaskResponseDto getTask(Integer taskId);

	public List<TaskResponseDto> getTasks();

	public TaskResponseDto createTask(TaskRequestDto taskRequestDto);
}
