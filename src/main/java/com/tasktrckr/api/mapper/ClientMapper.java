package com.tasktrckr.api.mapper;

import org.mapstruct.Mapper;

import com.tasktrckr.api.dto.client.ClientResponseDto;
import com.tasktrckr.api.jpa.entities.ClientEntity;

@Mapper(componentModel = "spring")
public interface ClientMapper {
	public ClientResponseDto toClientResponseDto(ClientEntity clientEntity);
}
