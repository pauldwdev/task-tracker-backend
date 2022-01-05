package com.tasttrckr.api.jpa.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "client")
@Data
public class ClientEntity {
	@Id
	private String clientId;
	private String clientName;

	@OneToMany(mappedBy="clientEntity", fetch = FetchType.LAZY)
	private List<ProjectEntity> projectEntityList;
}
