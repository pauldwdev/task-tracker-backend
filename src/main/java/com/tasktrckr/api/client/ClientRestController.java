package com.tasktrckr.api.client;

import java.util.List;

import org.jboss.logging.MDC;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tasktrckr.api.dto.client.ClientRequestDto;
import com.tasktrckr.api.dto.client.ClientResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/api/${tasktracker.api.version.base:v1}/clients")
@Tag(name = "Clients", description = "Endpoints for CRUD operations on clients")
public class ClientRestController {

	private final static String MDC_CONTEXT = "contextName";
	private final static String PROJECT_MDC = "clients";

	@Operation(summary = "Get a client by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found client with requested id", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponseDto.class)) }) })
	@GetMapping(path = "/{clientId}", produces = "application/json")
	public @ResponseBody ClientResponseDto getClient(@PathVariable String clientId) {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START getClient");
		ClientResponseDto client = null;
		log.info("--== END getClient");
		MDC.remove(MDC_CONTEXT);
		return client;
	}

	@Operation(summary = "Get all clients")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found clients to return", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientResponseDto.class))) }) })
	@GetMapping(produces = "application/json")
	public @ResponseBody List<ClientResponseDto> getClients() {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START getClients");
		List<ClientResponseDto> clientList = null;
		log.info("--== END getClients");
		MDC.remove(MDC_CONTEXT);
		return clientList;
	}

	@PutMapping(produces = "application/json")
	@Operation(summary = "Update a client")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Updated and return client", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponseDto.class)) }) })
	public @ResponseBody ClientResponseDto updateClient(@RequestBody ClientRequestDto clientRequestDto) {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START updateClient");
		ClientResponseDto client = null;
		log.info("--== END updateClient");
		MDC.remove(MDC_CONTEXT);
		return client;
	}

	@PostMapping(produces = "application/json")
	@Operation(summary = "Create a Client")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Create and return client", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ClientResponseDto.class)) }) })
	public @ResponseBody ClientResponseDto createClient(@RequestBody ClientRequestDto clientRequestDto) {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START createClient");
		ClientResponseDto client = null;
		log.info("--== END createClient");
		MDC.remove(MDC_CONTEXT);
		return client;
	}

	@DeleteMapping(path = "/{clientId}", produces = "application/json")
	@Operation(summary = "Delete a client")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Delete client") })
	public void deleteClient(@PathVariable String clientId) {
		MDC.put(MDC_CONTEXT, PROJECT_MDC);
		log.info("--== START deleteClient");
		// TODO implement logic
		log.info("--== END deleteClient");
		MDC.remove(MDC_CONTEXT);
	}
}
