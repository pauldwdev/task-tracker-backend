package com.tasktrckr.api.client;

import com.tasktrckr.api.dto.client.ClientResponseDto;

public interface ClientService {
	public ClientResponseDto getClient(String clientId);
}
