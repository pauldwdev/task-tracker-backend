package com.tasktrckr.api.task.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tasktrckr.api.configuration.GlobalControllerExceptionHandler;
import com.tasktrckr.api.dto.task.TaskRequestDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.task.TaskRestController;
import com.tasktrckr.api.task.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskRestContollerTest {

	@InjectMocks
	private TaskRestController taskRestController;

	private MockMvc mvc;

	@Mock
	private TaskService taskService;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(taskRestController)
				.setControllerAdvice(new GlobalControllerExceptionHandler())
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
	}

	@Test
	public void createTaskTest() throws Exception {

		TaskRequestDto request = new TaskRequestDto();
		request.setTaskId(1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(request);

		when(taskService.createTask(request)).thenReturn(new TaskResponseDto(1, null, null, null, null, null, null));

		mvc.perform(post("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content(json)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.taskId", is(1)));
		verify(taskService, times(1)).createTask(request);
	}

	@Test
	public void updateTaskTest() throws Exception {

		TaskRequestDto request = new TaskRequestDto();
		request.setTaskId(1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(request);

		when(taskService.updateTask(request)).thenReturn(new TaskResponseDto(1, null, null, null, null, null, null));

		mvc.perform(put("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content(json)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.taskId", is(1)));
		verify(taskService, times(1)).updateTask(request);
	}

	@Test
	public void deleteTaskTest() throws Exception {

		TaskRequestDto request = new TaskRequestDto();
		request.setTaskId(1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(request);

		doNothing().when(taskService).deleteTask(request.getTaskId());

		mvc.perform(delete("/api/v1/tasks" + "/" + request.getTaskId()).contentType(MediaType.APPLICATION_JSON)
				.content(json).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(taskService, times(1)).deleteTask(request.getTaskId());
	}

	@Test
	public void getTaskTest() throws Exception {

		TaskRequestDto request = new TaskRequestDto();
		request.setTaskId(1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(request);

		when(taskService.getTask(request.getTaskId()))
				.thenReturn(new TaskResponseDto(1, null, null, null, null, null, null));

		mvc.perform(get("/api/v1/tasks" + "/" + request.getTaskId()).contentType(MediaType.APPLICATION_JSON)
				.content(json).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.taskId", is(1)));
		verify(taskService, times(1)).getTask(request.getTaskId());
	}

	@Test
	public void getTasksTest() throws Exception {

		TaskRequestDto request = new TaskRequestDto();
		request.setTaskId(1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(request);

		List<TaskResponseDto> returnList = new ArrayList<>();
		returnList.add(new TaskResponseDto(1, null, null, null, null, null, null));
		returnList.add(new TaskResponseDto(2, null, null, null, null, null, null));

		when(taskService.getTasks()).thenReturn(returnList);

		mvc.perform(get("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content(json)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].taskId", is(1)));
		verify(taskService, times(1)).getTasks();
	}
}
