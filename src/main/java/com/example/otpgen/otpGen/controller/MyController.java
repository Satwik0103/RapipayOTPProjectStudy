package com.example.otpgen.otpGen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.otpgen.otpGen.entities.OTPBean;
import com.example.otpgen.otpGen.exception.EmailException;
import com.example.otpgen.otpGen.exception.InvalidChannelException;
import com.example.otpgen.otpGen.exception.InvalidUserException;
import com.example.otpgen.otpGen.exception.NoResourceException;
import com.example.otpgen.otpGen.exception.OTPInvalidException;
import com.example.otpgen.otpGen.exception.OTPTime;
import com.example.otpgen.otpGen.exception.PhoneNoDigitException;
import com.example.otpgen.otpGen.exception.PhoneNoLengthException;
import com.example.otpgen.otpGen.exception.TimestampException;
import com.example.otpgen.otpGen.service.OTPServiceInterface;

@RestController
public class MyController {

	@Autowired
	private OTPServiceInterface otpService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyController.class);
	
	@PostMapping("/OTPBean")
	public OTPBean generatedOtp(@RequestBody OTPBean otpentity) throws NoResourceException, OTPTime, EmailException, PhoneNoDigitException, PhoneNoLengthException, InvalidChannelException{
			LOGGER.info("Entered generate otp section");
			return this.otpService.generatedOTP(otpentity);
	}
	@GetMapping("/validate/{uid}/{otp}")
	public ResponseEntity<OTPBean>validateOtp(@PathVariable String uid,@PathVariable String otp) throws NoResourceException, OTPInvalidException, TimestampException, InvalidUserException {
		
			LOGGER.info("Entered validate eotp section");
			return this.otpService.validateOTP(uid,otp);
	
	}

}
