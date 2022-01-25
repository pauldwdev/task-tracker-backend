package com.tasktrckr.api.project;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasktrckr.api.dto.project.ProjectResponseDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.task.TaskRestController;
import com.tasktrckr.api.task.TaskService;

import io.swagger.v3.oas.annotations.Operation;
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
}
