package com.tasktrckr.api.project.test.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.tasktrckr.api.dto.project.ProjectRequestDto;
import com.tasktrckr.api.dto.project.ProjectResponseDto;
import com.tasktrckr.api.jpa.entities.ClientEntity;
import com.tasktrckr.api.jpa.entities.ProjectEntity;
import com.tasktrckr.api.jpa.repositories.ProjectRepository;
import com.tasktrckr.api.mapper.ProjectMapper;
import com.tasktrckr.api.project.ProjectServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

	@InjectMocks
	private ProjectServiceImpl projectService;

	@Mock
	private ProjectRepository projectRepository;

	@Mock
	private ProjectMapper projectMapper;

	@Test
	public void getProjectTest() {
		// data setup
		ProjectEntity projectEntity = this.createProjectEntity();
		int projectId = projectEntity.getProjectId();
		ProjectResponseDto response = new ProjectResponseDto(projectId, null, null, null, null, null, null);
		// mock setup
		when(projectRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(projectEntity));
		when(projectMapper.toProjectResponseDto(Mockito.any(ProjectEntity.class))).thenReturn(response);
		// call
		ProjectResponseDto projectReturned = projectService.getProject(projectId);
		// validation
		verify(projectRepository, times(1)).findById(projectId);
		verify(projectMapper, times(1)).toProjectResponseDto(projectEntity);
		Assertions.assertEquals(projectReturned.getProjectId(), projectId);
	}

	@Test
	public void getProjectsTest() {
		// data setup
		ProjectEntity p1 = this.createProjectEntity();
		ProjectEntity p2 = this.createProjectEntity();
		int projectId1 = p1.getProjectId();
		int projectId2 = p2.getProjectId();
		List<ProjectEntity> projectEntityList = Arrays.asList(p1, p2);
		List<ProjectResponseDto> projectResponseDtoList = Arrays.asList(
				new ProjectResponseDto(projectId1, null, null, null, null, null, null),
				new ProjectResponseDto(projectId2, null, null, null, null, null, null));
		// mock setup
		when(projectRepository.findAll()).thenReturn(projectEntityList);
		when(projectMapper.toProjectResponseDtoList(projectEntityList)).thenReturn(projectResponseDtoList);
		// call
		List<ProjectResponseDto> returned = projectService.getProjects();
		// validation
		verify(projectRepository, times(1)).findAll();
		verify(projectMapper, times(1)).toProjectResponseDtoList(projectEntityList);
		Assertions.assertEquals(returned, projectResponseDtoList);
	}



	@Test
	public void createProjectTest() {
		// data setup
		ProjectEntity e1 = this.createProjectEntity();
		int projectId = e1.getProjectId();
		ProjectRequestDto request = new ProjectRequestDto(projectId, null, null, null, null, null, null);
		ProjectResponseDto response = new ProjectResponseDto(projectId, null, null, null, null, null, null);
		// mock setup
		when(projectRepository.existsById(projectId)).thenReturn(false);
		when(projectRepository.saveAndFlush(e1)).thenReturn(e1);
		when(projectMapper.toProjectEntity(Mockito.any(ProjectRequestDto.class))).thenReturn(e1);
		when(projectMapper.toProjectResponseDto(Mockito.any(ProjectEntity.class))).thenReturn(response);
		// call
		ProjectResponseDto responseToTest = projectService.createProject(request);
		// validation
		Assertions.assertEquals(response, responseToTest);
		verify(projectRepository, times(1)).existsById(projectId);
		verify(projectRepository, times(1)).saveAndFlush(e1);
		verify(projectMapper, times(1)).toProjectEntity(request);
		verify(projectMapper, times(1)).toProjectResponseDto(e1);
	}
	private ProjectEntity createProjectEntity() {
		ProjectEntity p1 = new ProjectEntity();
		p1.setProjectId(RandomUtils.nextInt());
		ClientEntity c1 = new ClientEntity();
		c1.setClientId("CLI01");
		p1.setClientEntity(c1);
		return p1;
	}

}
