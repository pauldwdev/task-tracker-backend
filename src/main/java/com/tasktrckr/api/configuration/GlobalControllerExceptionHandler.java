package com.tasktrckr.api.configuration;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.tasktrckr.api.common.dto.GenericResponseBody;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler({ ResponseStatusException.class })
	public final void handleApiExceptions(Exception ex) throws Exception {

		throw ex;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class, AuthenticationException.class })
	public final GenericResponseBody handleAllExceptions(Exception ex) throws Exception {

		return new GenericResponseBody(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
				"Something went wrong, please try again after sometime.");
	}
}