package com.tasktrckr.api.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tasktrckr.api.dto.task.TaskRequestDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.jpa.repositories.ProjectRepository;
import com.tasktrckr.api.jpa.repositories.TaskRepository;
import com.tasktrckr.api.mapper.TaskMapper;
import com.tasttrckr.api.jpa.entities.TaskEntity;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ProjectRepository projectRepository;

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

	@Override
	public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
		// check if task is present already
		if (taskRepository.existsById(taskRequestDto.getTaskId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create new task with existing id.");
		}
		// check if project doesn't exit
		if (!projectRepository.existsById(taskRequestDto.getProjectId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Cannot create new task with non existing project.");
		}
		return taskMapper.toTaskResponseDto(taskRepository.saveAndFlush(taskMapper.toTaskEntity(taskRequestDto)));
	}

	@Override
	public TaskResponseDto updateTask(TaskRequestDto taskRequestDto) {
		// check if task is not present
		if (!taskRepository.existsById(taskRequestDto.getTaskId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Cannot update task. Task with provided ID does not exist.");
		}
		return taskMapper.toTaskResponseDto(taskRepository.saveAndFlush(taskMapper.toTaskEntity(taskRequestDto)));
	}

}
