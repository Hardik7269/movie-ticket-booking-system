package com.project.bms.exceptations;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ApiError( LocalDateTime timestamp,
	    int status,
	    String message,
	    String path ) {

}
