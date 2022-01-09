package com.tasktrckr.api.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasktrckr.api.dto.task.TaskRequestDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/${tasktracker.api.version.base:v1}/tasks")
@Tag(name = "Tasks", description = "Endpoints for CRUD operations on tasks")
public class TaskRestController {

	@Autowired
	private TaskService taskService;

	@Operation(summary = "Get a task by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found task with requested id", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDto.class)) }) })
	@GetMapping(path = "/{taskId}", produces = "application/json")
	public @ResponseBody TaskResponseDto getTask(@PathVariable int taskId) {
		return taskService.getTask(taskId);

	}

	@Operation(summary = "Get all tasks")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found tasks to return", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TaskResponseDto.class))) }) })
	@GetMapping(produces = "application/json")
	public @ResponseBody List<TaskResponseDto> getTasks() {
		return taskService.getTasks();
	}

	@PostMapping(produces = "application/json")
	@Operation(summary = "Update a task")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated and return task", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDto.class)) }) })

	public @ResponseBody TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto) {
		return taskService.createTask(taskRequestDto);
	}

	public @ResponseBody TaskResponseDto updateTask(@RequestBody TaskRequestDto taskRequestDto) {
		return taskService.updateTask(taskRequestDto);
	}

}
