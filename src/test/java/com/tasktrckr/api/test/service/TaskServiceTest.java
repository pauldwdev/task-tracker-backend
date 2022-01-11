package com.tasktrckr.api.test.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.jpa.repositories.TaskRepository;
import com.tasktrckr.api.mapper.TaskMapper;
import com.tasktrckr.api.task.TaskServiceImpl;
import com.tasttrckr.api.jpa.entities.TaskEntity;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

	@InjectMocks
	private TaskServiceImpl taskService;

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private TaskMapper taskMapper;

	@Test
	public void getTaskTest() {
		int taskId = 1;
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setTaskId(taskId);
		TaskResponseDto response = new TaskResponseDto(taskId, null, null, null, null, null);
		when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(taskEntity));
		when(taskMapper.toTaskResponseDto(Mockito.any(TaskEntity.class))).thenReturn(response);
		TaskResponseDto taskReturned = taskService.getTask(taskId);
		verify(taskRepository, times(1)).findById(taskId);
		verify(taskMapper, times(1)).toTaskResponseDto(taskEntity);
		Assertions.assertEquals(taskReturned.getTaskId(), taskId);
	}

	@Test
	public void getTasksTest() {
		TaskEntity e1 = new TaskEntity();
		e1.setTaskId(1);
		TaskEntity e2 = new TaskEntity();
		e2.setTaskId(2);
		List<TaskEntity> taskEntityList = Arrays.asList(e1, e2);
		List<TaskResponseDto> taskResponseDtoList = Arrays.asList(new TaskResponseDto(1, null, null, null, null, null),
				new TaskResponseDto(2, null, null, null, null, null));
		when(taskRepository.findAll()).thenReturn(taskEntityList);
		when(taskMapper.toTaskResponseDtoList(taskEntityList)).thenReturn(taskResponseDtoList);
		List<TaskResponseDto> returned = taskService.getTasks();
		verify(taskRepository, times(1)).findAll();
		verify(taskMapper, times(1)).toTaskResponseDtoList(taskEntityList);
		Assertions.assertEquals(returned, taskResponseDtoList);
	}

}
