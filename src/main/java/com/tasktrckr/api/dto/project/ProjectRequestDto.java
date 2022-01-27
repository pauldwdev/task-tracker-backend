package com.tasktrckr.api.dto.project;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestDto {
	private Integer projectId;
	private String projectName;
	private Timestamp dateCreated;
	private Timestamp dateStart;
	private Timestamp dateEstEnd;
	private Timestamp dateCompleted;
	private String clientId;
}
