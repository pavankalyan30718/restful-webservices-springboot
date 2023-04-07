package com.pk.restservices.restfulwebservices.exception;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.pk.restservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}	
    
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				"Total Errors:" +ex.getErrorCount()+" The First Error is:"+ ex.getFieldError().getDefaultMessage(), request.getDescription(false));
	
		//ex.getFiedErrors()
		
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
