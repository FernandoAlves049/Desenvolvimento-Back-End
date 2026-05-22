package com.suaapi.conversor.exception;

public class UnsupportedTemperatureTargetException extends RuntimeException {

	private final String details;

	public UnsupportedTemperatureTargetException(String message, String details) {
		super(message);
		this.details = details;
	}

	public String getDetails() {
		return details;
	}
}