package com.tasktrckr.api.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponseBody {
	int status;
	String error;
	String message;
}
