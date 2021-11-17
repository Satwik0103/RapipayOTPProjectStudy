package com.example.otpgen.otpGen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.otpgen.otpGen.entities.OTPBean;
import com.example.otpgen.otpGen.exception.EmailException;
import com.example.otpgen.otpGen.exception.InvalidChannelException;
import com.example.otpgen.otpGen.exception.InvalidUserException;
import com.example.otpgen.otpGen.exception.OTPInvalidException;
import com.example.otpgen.otpGen.exception.OTPTime;
import com.example.otpgen.otpGen.exception.PhoneNoDigitException;
import com.example.otpgen.otpGen.exception.PhoneNoLengthException;
import com.example.otpgen.otpGen.exception.TimestampException;
import com.example.otpgen.otpGen.service.OTPService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OtpServiceTest {


	@Autowired
	OTPService otpService;
	
	
	OTPBean otpTest1=new OTPBean("email","12349","vrinda@gmail.com");
	OTPBean otpTest2=new OTPBean("email","23491","virenda@gmail.com");
	OTPBean otpTest3=new OTPBean("email","34912","brinda@gmail.com");
	OTPBean otpTest4=new OTPBean("email","49123","bdfrindagmail.com");
	OTPBean otpTest5=new OTPBean("email","91234","binnda@gmail.com");
	OTPBean otpTest6=new OTPBean("sms","66666","828706439s");
	OTPBean otpTest7=new OTPBean("sms","55555","999988877");
	OTPBean otpTest8=new OTPBean("jg","22222","22335557673");
	
	
	
	
	@Test
	void GenerateOtp(){
		String otp=otpService.generateOTP();
		assertTrue(otp.length()==6);
		
	
	}
	
	@Test
	void testGenerateOtpService() {
		
		try {
			Assertions.assertThrows(InvalidChannelException.class, ()->{
				otpService.generatedOTP(otpTest8);
			});
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	void testValidateOtpService() {

		OTPService otpService=new OTPService();
		try {
			otpService.generatedOTP(otpTest7);
			Assertions.assertThrows(TimestampException.class,()->{
				otpService.validateOTP(otpTest7.getUid(),otpTest7.getOtp());
			});
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
