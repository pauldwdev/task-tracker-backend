package com.tasktrckr.api.dto.task;

import java.sql.Timestamp;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tasttrckr.api.jpa.entities.ProjectEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseDto {
	private Integer taskId;
	private String taskName;
	private String taskDetails;
	private Timestamp dateCreated;
	private Timestamp dateCompleted;
	private Timestamp dateStarted;
}