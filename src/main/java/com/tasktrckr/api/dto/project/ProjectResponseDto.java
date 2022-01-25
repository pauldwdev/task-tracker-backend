package com.tasktrckr.api.dto.project;

import java.sql.Timestamp;
import java.util.List;

import com.tasktrckr.api.dto.task.TaskResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {
	private Integer projectId;
	private String projectName;
	private Timestamp dateCreated;
	private Timestamp dateStart;
	private Timestamp dateEstEnd;
	private Timestamp dateCompleted;
	private List<TaskResponseDto> taskList;
}
