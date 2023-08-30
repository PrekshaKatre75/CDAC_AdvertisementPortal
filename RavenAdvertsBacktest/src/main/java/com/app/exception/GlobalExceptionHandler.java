package com.app.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import custom_exceptions.ResourceNotFoundException;
import com.app.raven.dto.*;

//how to tell Sc following class is spring bean,
//containing common advice to all rest controllers
//regarding centralized exception handler
@RestControllerAdvice
public class GlobalExceptionHandler {
	//this class can contain single and plus multiple exce handling methods 
	//how to tell SC, following method: exc handling method
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
	{
		Map<String,String> hm=e.getFieldErrors().
				stream().collect(Collectors.toMap(f->f.getField(),f->f.getDefaultMessage()));
		
		
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(e.getFieldErrors());
		
	}
	@ExceptionHandler(custom_exceptions.ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(custom_exceptions.ResourceNotFoundException e)
	{
		System.out.println("handle res not found"+e);
	return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(new com.app.raven.dto.ApiResponse(e.getMessage()));
		
	}

}
