package com.tasktrckr.api.dto.client;

import java.util.List;

import com.tasktrckr.api.dto.project.ProjectResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {
	private String clientId;
	private String clientName;
	private List<ProjectResponseDto> projectList;
}
