package com.tasktrckr.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tasktrckr.api.dto.client.ClientResponseDto;
import com.tasktrckr.api.dto.task.TaskResponseDto;
import com.tasktrckr.api.jpa.entities.ClientEntity;
import com.tasktrckr.api.jpa.entities.TaskEntity;
import com.tasktrckr.api.jpa.repositories.ClientRepository;
import com.tasktrckr.api.mapper.ClientMapper;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientMapper clientMapper;

	@Override
	public ClientResponseDto getClient(String clientId) {
		ClientEntity client = clientRepository.findById(clientId).orElse(null);
		if (client == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found");
		}
		return clientMapper.toClientResponseDto(client);
	}
}
