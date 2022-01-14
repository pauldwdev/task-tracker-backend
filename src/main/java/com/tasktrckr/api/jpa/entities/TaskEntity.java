package com.tasktrckr.api.jpa.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class TaskEntity {

	@Id
	private Integer taskId;
	private String taskName;
	private String taskDetails;
	private Timestamp dateCreated;
	private Timestamp dateCompleted;
	private Timestamp dateStarted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId")
	private ProjectEntity projectEntity;
}
