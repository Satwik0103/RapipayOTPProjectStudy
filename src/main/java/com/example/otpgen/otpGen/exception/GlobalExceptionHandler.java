package com.example.otpgen.otpGen.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(NoResourceException.class)
	public ResponseEntity<?> resourceNotFoundException(NoResourceException ex, WebRequest web){
		ErrorDetails error=new ErrorDetails(new Date(), ex.getMessage(), web.getDescription(false));
		return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalException(NoResourceException ex, WebRequest web){
		ErrorDetails error=new ErrorDetails(new Date(), ex.getMessage(), web.getDescription(false));
		return new ResponseEntity<> (error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EmailException.class)
	public ResponseEntity<?> resourceNotFoundException(EmailException ex, WebRequest web){
		ErrorDetails error=new ErrorDetails(new Date(), ex.getMessage(), web.getDescription(false));
		return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidChannelException.class)
	public ResponseEntity<?> resourceNotFoundException(InvalidChannelException ex, WebRequest web){
		ErrorDetails error=new ErrorDetails(new Date(), ex.getMessage(), web.getDescription(false));
		return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(OTPTime.class)
	public ResponseEntity<?> resourceNotFoundException(OTPTime ex, WebRequest web){
		ErrorDetails error=new ErrorDetails(new Date(), ex.getMessage(), web.getDescription(false));
		return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(OTPInvalidException.class)
	public ResponseEntity<?> resourceNotFoundException(OTPInvalidException ex, WebRequest web){
		ErrorDetails error=new ErrorDetails(new Date(), ex.getMessage(), web.getDescription(false));
		return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
	}


	@ExceptionHandler(PhoneNoDig.class)
	public ResponseEntity<?> resourceNotFoundException(OTPTime ex, WebRequest web){
		ErrorDetails error=new ErrorDetails(new Date(), ex.getMessage(), web.getDescription(false));
		return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
	}

}
