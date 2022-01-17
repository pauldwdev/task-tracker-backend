package com.tasktrckr.api.task.test.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aspectj.lang.annotation.Before;
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
	public void validateCartonTest() throws Exception {

		TaskRequestDto request = new TaskRequestDto();
		request.setTaskId(1);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(request);

		when(taskService.createTask(request)).thenReturn(new TaskResponseDto(1, null, null, null, null, null, null));

		mvc.perform(post("/api/v1/tasks").contentType(MediaType.APPLICATION_JSON).content(json)
				.characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.taskId", is(1)));
		verify(taskService, times(1)).createTask(request);
	}

}
