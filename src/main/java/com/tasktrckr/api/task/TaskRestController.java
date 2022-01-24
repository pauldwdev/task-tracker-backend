package com.tasktrckr.api.task;

import java.util.List;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/${tasktracker.api.version.base:v1}/tasks")
@Tag(name = "Tasks", description = "Endpoints for CRUD operations on tasks")
public class TaskRestController {

	private final static String MDC_CONTEXT = "contextName";
	private final static String TASK_MDC = "tasks";

	@Autowired
	private TaskService taskService;

	@Operation(summary = "Get a task by its id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found task with requested id", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDto.class)) }) })
	@GetMapping(path = "/{taskId}", produces = "application/json")
	public @ResponseBody TaskResponseDto getTask(@PathVariable int taskId) {
		MDC.put(MDC_CONTEXT, TASK_MDC);
		log.info("--== START getTask");
		TaskResponseDto task = taskService.getTask(taskId);
		log.info("--== END getTask");
		MDC.remove(MDC_CONTEXT);
		return task;
	}

	@Operation(summary = "Get all tasks")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found tasks to return", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TaskResponseDto.class))) }) })
	@GetMapping(produces = "application/json")
	public @ResponseBody List<TaskResponseDto> getTasks() {
		MDC.put(MDC_CONTEXT, TASK_MDC);
		log.info("--== START getTasks");
		List<TaskResponseDto> taskList = taskService.getTasks();
		log.info("--== END getTasks");
		MDC.remove(MDC_CONTEXT);
		return taskList;
	}

	@PutMapping(produces = "application/json")
	@Operation(summary = "Update a task")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated and return task", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDto.class)) }) })
	public @ResponseBody TaskResponseDto updateTask(@RequestBody TaskRequestDto taskRequestDto) {
		MDC.put(MDC_CONTEXT, TASK_MDC);
		log.info("--== START updateTask");
		TaskResponseDto task = taskService.updateTask(taskRequestDto);
		log.info("--== END updateTask");
		MDC.remove(MDC_CONTEXT);
		return task;
	}

	@PostMapping(produces = "application/json")
	@Operation(summary = "Create a task")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create and return task", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = TaskResponseDto.class)) }) })
	public @ResponseBody TaskResponseDto createTask(@RequestBody TaskRequestDto taskRequestDto) {
		MDC.put(MDC_CONTEXT, TASK_MDC);
		log.info("--== START createTask");
		TaskResponseDto task = taskService.createTask(taskRequestDto);
		log.info("--== END createTask");
		MDC.remove(MDC_CONTEXT);
		return task;
	}

	@DeleteMapping(path = "/{taskId}", produces = "application/json")
	@Operation(summary = "Delete a task")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete task") })
	public void deleteTask(@PathVariable int taskId) {
		MDC.put(MDC_CONTEXT, TASK_MDC);
		log.info("--== START deleteTask");
		taskService.deleteTask(taskId);
		log.info("--== END deleteTask");
		MDC.remove(MDC_CONTEXT);
	}

}
