package com.project.bms.exceptations;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private ResponseEntity<ApiError> apiErrorGenerator(String message, int statusCode, HttpStatus httpStatus,
			HttpServletRequest req) {
		ApiError res = ApiError.builder().timestamp(LocalDateTime.now()).status(statusCode).message(message)
				.path(req.getRequestURI()).build();

		return ResponseEntity.status(httpStatus).body(res);
	}

	@ExceptionHandler(value = MovieNotFoundException.class)
	public ResponseEntity<ApiError> moveiNotFoundException(MovieNotFoundException excep, HttpServletRequest req) {
		return apiErrorGenerator(excep.getMessage(), 404, HttpStatus.NOT_FOUND, req);

	}

	@ExceptionHandler(value = NoTicketsAvailableException.class)
	public ResponseEntity<ApiError> noTicketAvailableException(NoTicketsAvailableException excep,
			HttpServletRequest req) {
		return apiErrorGenerator(excep.getMessage(), 409, HttpStatus.CONFLICT, req);

	}

	@ExceptionHandler(value = SeatNotAvailableException.class)
	public ResponseEntity<ApiError> seatNotAvailableExceptation(SeatNotAvailableException excep,
			HttpServletRequest req) {

		return apiErrorGenerator(excep.getMessage(), 409, HttpStatus.CONFLICT, req);

	}

	@ExceptionHandler(value = ShowNotFoundException.class)
	public ResponseEntity<ApiError> showNotFoundExceptation(ShowNotFoundException excep, HttpServletRequest req) {

		return apiErrorGenerator(excep.getMessage(), 404, HttpStatus.NOT_FOUND, req);

	}

	@ExceptionHandler(value = TheaterNotFoundException.class)
	public ResponseEntity<ApiError> theaterNotFoundExceptation(TheaterNotFoundException excep, HttpServletRequest req) {

		return apiErrorGenerator(excep.getMessage(), 404, HttpStatus.NOT_FOUND, req);

	}

	@ExceptionHandler(value = UserAddtionFailedException.class)
	public ResponseEntity<ApiError> userAddtionFailedExceptation(UserAddtionFailedException excep,
			HttpServletRequest req) {
		return apiErrorGenerator(excep.getMessage(), 500, HttpStatus.INTERNAL_SERVER_ERROR, req);

	}

	@ExceptionHandler(value = UserAlreadyExistsException.class)
	public ResponseEntity<ApiError> userAlreadyExistsExceptation(UserAlreadyExistsException excep,
			HttpServletRequest req) {

		return apiErrorGenerator(excep.getMessage(), 409, HttpStatus.CONFLICT, req);

	}

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ApiError> userNotFoundException(UserNotFoundException excep, HttpServletRequest req) {

		return apiErrorGenerator(excep.getMessage(), 404, HttpStatus.NOT_FOUND, req);

	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ApiError> userNotFoundException(Exception excep, HttpServletRequest req) {

		return apiErrorGenerator("Something went wrong!!", 500, HttpStatus.INTERNAL_SERVER_ERROR, req);

	}

}