package com.pk.restservices.restfulwebservices.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

public class ErrorDetails {

	//structure I wanted to create
	//whenever an exception occurs, I want it to return in this structure
		//timestamp
		//message
		//details
	
	private LocalDateTime timestamp;
	private String message;
	private String details;

	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
}