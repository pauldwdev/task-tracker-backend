package com.tasktrckr.api.task;

import com.tasktrckr.api.dto.task.TaskResponseDto;

public interface TaskService {
	public TaskResponseDto getTask(Integer taskId);
}
