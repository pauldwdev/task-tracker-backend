package com.tasttrckr.api.jpa.entities;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "project")
public class ProjectEntity {
	@Id
	private Integer projectId;
	private String projectName;
	private Timestamp dateCreated;
	private Timestamp dateStart;
	private Timestamp dateEstEnd;
	private Timestamp dateCompleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="clientId")
	private ClientEntity clientEntity;

	@OneToMany(mappedBy = "projectEntity" , fetch = FetchType.LAZY)
	private List<TaskEntity> taskEntityList;
}
