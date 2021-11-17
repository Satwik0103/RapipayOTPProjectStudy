package com.example.otpgen.otpGen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoResourceException extends Exception {


	public NoResourceException(String message) {
		
	}
	
	
	

}
