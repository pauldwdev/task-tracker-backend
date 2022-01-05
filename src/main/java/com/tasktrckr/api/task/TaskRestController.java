package com.tasktrckr.api.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.jpa.repositories.TaskRepository;
import com.tasttrckr.api.jpa.entities.TaskEntity;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskRestController {

	@Autowired
	private TaskService taskService;

	@GetMapping(path = "/{taskId}", produces = "application/json")
	public @ResponseBody TaskResponseDto getTask(@PathVariable int taskId) {
		return taskService.getTask(taskId);
	}
}
