package com.example.otpgen.otpGen.exception;


public class OTPTime extends Exception{

	@Override
	public String getMessage() {
		return "Please request after 2 minutes";
	}

	
}
