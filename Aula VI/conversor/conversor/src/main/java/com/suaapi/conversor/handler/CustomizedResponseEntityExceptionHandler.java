package com.suaapi.conversor.handler;

import com.suaapi.conversor.exception.ExceptionResponse;
import com.suaapi.conversor.exception.UnsupportedTemperatureTargetException;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UnsupportedTemperatureTargetException.class)
	public final ResponseEntity<ExceptionResponse> handleUnsupportedTemperatureTargetException(
			UnsupportedTemperatureTargetException exception, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				exception.getMessage(),
				request.getDescription(false) + " - " + exception.getDetails());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception exception, WebRequest request) {
		ExceptionResponse response = new ExceptionResponse(
				new Date(),
				exception.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}