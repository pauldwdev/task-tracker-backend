package com.tasktrckr.api.task.test.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import com.tasktrckr.api.dto.task.TaskRequestDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.jpa.entities.ProjectEntity;
import com.tasktrckr.api.jpa.entities.TaskEntity;
import com.tasktrckr.api.jpa.repositories.ProjectRepository;
import com.tasktrckr.api.jpa.repositories.TaskRepository;
import com.tasktrckr.api.mapper.TaskMapper;
import com.tasktrckr.api.task.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

	@InjectMocks
	private TaskServiceImpl taskService;

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private TaskMapper taskMapper;

	@Test
	public void getTaskTest() {
		// data setup
		TaskEntity taskEntity = this.createTaskEntity();
		int taskId = taskEntity.getTaskId();
		TaskResponseDto response = new TaskResponseDto(taskId, null, null, null, null, null, null);
		// mock setup
		when(taskRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(taskEntity));
		when(taskMapper.toTaskResponseDto(Mockito.any(TaskEntity.class))).thenReturn(response);
		// call
		TaskResponseDto taskReturned = taskService.getTask(taskId);
		// validation
		verify(taskRepository, times(1)).findById(taskId);
		verify(taskMapper, times(1)).toTaskResponseDto(taskEntity);
		Assertions.assertEquals(taskReturned.getTaskId(), taskId);
	}

	@Test
	public void getTasksTest() {
		// data setup
		TaskEntity e1 = this.createTaskEntity();
		TaskEntity e2 = this.createTaskEntity();
		int taskId1 = e1.getTaskId();
		int taskId2 = e2.getTaskId();
		List<TaskEntity> taskEntityList = Arrays.asList(e1, e2);
		List<TaskResponseDto> taskResponseDtoList = Arrays.asList(
				new TaskResponseDto(taskId1, null, null, null, null, null, null),
				new TaskResponseDto(taskId2, null, null, null, null, null, null));
		// mock setup
		when(taskRepository.findAll()).thenReturn(taskEntityList);
		when(taskMapper.toTaskResponseDtoList(taskEntityList)).thenReturn(taskResponseDtoList);
		// call
		List<TaskResponseDto> returned = taskService.getTasks();
		// validation
		verify(taskRepository, times(1)).findAll();
		verify(taskMapper, times(1)).toTaskResponseDtoList(taskEntityList);
		Assertions.assertEquals(returned, taskResponseDtoList);
	}

	@Test
	public void createTaskTest() {
		// data setup
		TaskEntity e1 = this.createTaskEntity();
		int taskId = e1.getTaskId();
		int projectId = e1.getProjectEntity().getProjectId();
		TaskRequestDto request = new TaskRequestDto(taskId, projectId, null, null, null, null, null);
		TaskResponseDto response = new TaskResponseDto(taskId, projectId, null, null, null, null, null);
		// mock setup
		when(taskRepository.existsById(taskId)).thenReturn(false);
		when(projectRepository.existsById(projectId)).thenReturn(true);
		when(taskRepository.saveAndFlush(e1)).thenReturn(e1);
		when(taskMapper.toTaskEntity(Mockito.any(TaskRequestDto.class))).thenReturn(e1);
		when(taskMapper.toTaskResponseDto(Mockito.any(TaskEntity.class))).thenReturn(response);
		// call
		TaskResponseDto responseToTest = taskService.createTask(request);
		// validation
		Assertions.assertEquals(response, responseToTest);
		verify(taskRepository, times(1)).existsById(taskId);
		verify(projectRepository, times(1)).existsById(projectId);
		verify(taskRepository, times(1)).saveAndFlush(e1);
		verify(taskMapper, times(1)).toTaskEntity(request);
		verify(taskMapper, times(1)).toTaskResponseDto(e1);
	}

	@Test
	public void createTaskResponseStatusException1Test() {
		TaskEntity e1 = this.createTaskEntity();
		int taskId = e1.getTaskId();
		int projectId = e1.getProjectEntity().getProjectId();
		TaskRequestDto request = new TaskRequestDto(taskId, null, null, null, null, null, projectId);
		when(taskRepository.existsById(taskId)).thenReturn(true);
		Exception exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
			TaskResponseDto responseToTest = taskService.createTask(request);
		});
		Assertions.assertEquals("400 BAD_REQUEST \"Cannot create new task with existing id.\"", exception.getMessage());
	}

	@Test
	public void createTaskResponseStatusException2Test() {
		TaskEntity e1 = this.createTaskEntity();
		int taskId = e1.getTaskId();
		int projectId = e1.getProjectEntity().getProjectId();
		TaskRequestDto request = new TaskRequestDto(taskId, null, null, null, null, null, projectId);
		when(taskRepository.existsById(taskId)).thenReturn(false);
		when(projectRepository.existsById(projectId)).thenReturn(false);
		Exception exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
			TaskResponseDto responseToTest = taskService.createTask(request);
		});
		Assertions.assertEquals("400 BAD_REQUEST \"Cannot create new task with non existing project.\"",
				exception.getMessage());
	}

	@Test
	public void updateTaskTest() {
		TaskEntity e1 = this.createTaskEntity();
		int taskId = e1.getTaskId();
		int projectId = e1.getProjectEntity().getProjectId();
		TaskRequestDto request = new TaskRequestDto(taskId, null, null, null, null, null, projectId);
		TaskResponseDto response = new TaskResponseDto(taskId, projectId, null, null, null, null, null);
		when(taskRepository.existsById(taskId)).thenReturn(true);
		when(taskRepository.saveAndFlush(e1)).thenReturn(e1);
		when(taskMapper.toTaskEntity(Mockito.any(TaskRequestDto.class))).thenReturn(e1);
		when(taskMapper.toTaskResponseDto(Mockito.any(TaskEntity.class))).thenReturn(response);
		TaskResponseDto responseToTest = taskService.updateTask(request);
		Assertions.assertEquals(response, responseToTest);
		verify(taskRepository, times(1)).existsById(taskId);
		verify(taskRepository, times(1)).saveAndFlush(e1);
		verify(taskMapper, times(1)).toTaskEntity(request);
		verify(taskMapper, times(1)).toTaskResponseDto(e1);
	}

	@Test
	public void updateTaskResponseStatusException1Test() {
		TaskEntity e1 = this.createTaskEntity();
		int taskId = e1.getTaskId();
		int projectId = e1.getProjectEntity().getProjectId();
		TaskRequestDto request = new TaskRequestDto(taskId, null, null, null, null, null, projectId);
		when(taskRepository.existsById(taskId)).thenReturn(false);
		Exception exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
			TaskResponseDto responseToTest = taskService.updateTask(request);
		});
		Assertions.assertEquals("400 BAD_REQUEST \"Cannot update task. Task with provided ID does not exist.\"",
				exception.getMessage());
	}

	@Test
	public void deleteTaskTest() {
		TaskEntity e1 = this.createTaskEntity();
		int taskId = e1.getTaskId();
		when(taskRepository.existsById(taskId)).thenReturn(true);
		doNothing().when(taskRepository).deleteById(taskId);
		taskService.deleteTask(taskId);
		verify(taskRepository, times(1)).existsById(taskId);
		verify(taskRepository, times(1)).deleteById(taskId);
	}

	@Test
	public void deleteTaskResponseStatusException1Test() {
		TaskEntity e1 = this.createTaskEntity();
		int taskId = e1.getTaskId();
		when(taskRepository.existsById(taskId)).thenReturn(false);
		Exception exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
			taskService.deleteTask(taskId);
		});
		Assertions.assertEquals("400 BAD_REQUEST \"Cannot delete task. Task with provided ID does not exist.\"",
				exception.getMessage());
	}

	private TaskEntity createTaskEntity() {
		TaskEntity e1 = new TaskEntity();
		e1.setTaskId(RandomUtils.nextInt());
		ProjectEntity p1 = new ProjectEntity();
		p1.setProjectId(RandomUtils.nextInt());
		e1.setProjectEntity(p1);
		return e1;
	}

}
