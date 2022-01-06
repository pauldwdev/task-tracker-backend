package com.tasktrckr.api.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.jpa.repositories.TaskRepository;
import com.tasktrckr.api.mapper.TaskMapper;
import com.tasttrckr.api.jpa.entities.TaskEntity;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskMapper taskMapper;

	@Override
	public TaskResponseDto getTask(Integer taskId) {

		TaskEntity task = taskRepository.findById(taskId).orElse(null);
		if (task == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found");
		}

		return taskMapper.toTaskResponseDto(task);
	}

	@Override
	public List<TaskResponseDto> getTasks() {
		return taskMapper.toTaskResponseDtoList(taskRepository.findAll());
	}

}
