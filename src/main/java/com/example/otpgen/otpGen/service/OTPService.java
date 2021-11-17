package com.example.otpgen.otpGen.service;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.otpgen.otpGen.dao.otpDao;
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

@Service
public class OTPService implements OTPServiceInterface {
	
	@Value("${app.gen}")
	public int gOtp;
	
	@Value("${app.val}")
	public int vOtp;

	@Override
	public String generateOTP() {
		long otp;
		Random random = new Random();
		otp = 100000 + random.nextInt(900000);
		return String.valueOf(otp);
	}

	@Autowired
	private otpDao otpDao;

	public OTPBean generatedOTP(OTPBean otpEntities) throws NoResourceException, OTPTime, EmailException, PhoneNoDigitException,PhoneNoLengthException,InvalidChannelException{
		
		OTPBean otp=otpDao.findById(otpEntities.getUid()).orElse(null);
		if(otp!=null) {
			if(((System.currentTimeMillis()-otp.getTimestamp())/60000)<gOtp)
				throw new OTPTime();
		}
		if (otpEntities.getChannelName().equalsIgnoreCase("email")) {
			Pattern emailPattern = Pattern.compile(
					"[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\"\\.]]+");
			Matcher m = emailPattern.matcher(otpEntities.getUid());
			if (!m.matches()) {
				throw new EmailException();
			}
		} else if (otpEntities.getChannelName().equalsIgnoreCase("sms")) {
			if (!otpEntities.getUid().matches("[0-9]+"))
				throw new PhoneNoDigitException();
			if (otpEntities.getUid().length() != 10) {
				throw new PhoneNoLengthException();
			}
		} else {
			throw new InvalidChannelException();
		}
		otpEntities.setOtp(generateOTP());
		System.out.println(otpEntities.getOtp());
		otpEntities.setTimestamp();
		this.otpDao.save(otpEntities);
		//System.out.println(otpEntities);
		return otpEntities;
	}

	@Override
	public ResponseEntity<OTPBean> validateOTP(String uid, String otp) throws OTPInvalidException,TimestampException, InvalidUserException, NoResourceException {
		long timestamp = System.currentTimeMillis();
		OTPBean otpBean = otpDao.findById(uid).orElseThrow(() -> new InvalidUserException());

		long timestampStored = otpBean.getTimestamp();
		if ((timestamp - timestampStored) / 60000 < vOtp) {
			String otpStored = otpBean.getOtp();
			if (otpStored.equals(otp)) {
				return ResponseEntity.ok().body(otpBean);
			} else {
				throw new OTPInvalidException();
				// return ResponseEntity.badRequest().body(otpBean);
			}
		} else {
			throw new TimestampException();
			// return ResponseEntity.badRequest().body(otpBean);}
		}
	}
}