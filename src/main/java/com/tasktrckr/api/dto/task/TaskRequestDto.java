package com.tasktrckr.api.dto.task;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {
	private Integer taskId;
	private Integer projectId;
	private String taskName;
	private String taskDetails;
	private Timestamp dateCreated;
	private Timestamp dateCompleted;
	private Timestamp dateStarted;
	
}
