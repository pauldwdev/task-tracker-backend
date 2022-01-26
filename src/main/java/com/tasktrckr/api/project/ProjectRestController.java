package com.tasktrckr.api.project;

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

import com.tasktrckr.api.dto.project.ProjectRequestDto;
import com.tasktrckr.api.dto.project.ProjectResponseDto;
import com.tasktrckr.api.dto.task.TaskRequestDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.task.TaskRestController;
import com.tasktrckr.api.task.TaskService;

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
@RequestMapping(path = "/api/${tasktracker.api.version.base:v1}/projects")
@Tag(name = "Projects", description = "Endpoints for CRUD operations on projects")
public class ProjectRestController {
	private final static String MDC_CONTEXT = "contextName";
	private final static String PROJECT_MDC = "projects";

	@Autowired
	private ProjectService projectService;

	@Operation(summary = "Get a project by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found project with requested id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProjectResponseDto.class)) }) })
	@GetMapping(path = "/{projectId}", produces = "application/json")
	public @ResponseBody ProjectResponseDto getProject(@PathVariable int projectId) {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START getProject");
		ProjectResponseDto project = projectService.getProject(projectId);
		log.info("--== END getProject");
		MDC.remove(MDC_CONTEXT);
		return project;
	}

	@Operation(summary = "Get all projects")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found projects to return", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProjectResponseDto.class))) }) })
	@GetMapping(produces = "application/json")
	public @ResponseBody List<ProjectResponseDto> getProjects() {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START getProjects");
		List<ProjectResponseDto> projectList = projectService.getProjects();
		log.info("--== END getProjects");
		MDC.remove(MDC_CONTEXT);
		return projectList;
	}

	@PutMapping(produces = "application/json")
	@Operation(summary = "Update a project")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated and return project", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProjectResponseDto.class)) }) })
	public @ResponseBody ProjectResponseDto updateProject(@RequestBody ProjectRequestDto projectRequestDto) {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START updateProject");
		ProjectResponseDto project = projectService.updateProject(projectRequestDto);
		log.info("--== END updateProject");
		MDC.remove(MDC_CONTEXT);
		return project;
	}

	@PostMapping(produces = "application/json")
	@Operation(summary = "Create a Project")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create and return project", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ProjectResponseDto.class)) }) })
	public @ResponseBody ProjectResponseDto createProject(@RequestBody ProjectRequestDto projectRequestDto) {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START createProject");
		ProjectResponseDto project = projectService.createProject(projectRequestDto);
		log.info("--== END createProject");
		MDC.remove(MDC_CONTEXT);
		return project;
	}

	@DeleteMapping(path = "/{projectId}", produces = "application/json")
	@Operation(summary = "Delete a project")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete project") })
	public void deleteProject(@PathVariable int projectId) {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START deleteProject");
		projectService.deleteProject(projectId);
		log.info("--== END deleteProject");
		MDC.remove(MDC_CONTEXT);
	}
}
