package com.example.otpgen.otpGen.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OTPBean {
	@Id
	private String uid;
	private String oid;
	private String channelName;
	private String otp;
	private long timestamp;
	public OTPBean(String uid, String oid, String channelName) {
		this.uid = uid;
		this.oid = oid;
		this.channelName = channelName;
	}
	
	public OTPBean(String uid, String oid, String channelName, String otp) {
		this.uid = uid;
		this.oid = oid;
		this.channelName = channelName;
		this.otp = otp;
	}

	
	public OTPBean(String uid, String oid, String channelName, String otp, long timestamp) {
		super();
		this.uid = uid;
		this.oid = oid;
		this.channelName = channelName;
		this.otp = otp;
		this.timestamp = timestamp;
	}

	public OTPBean() {
	}
	

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp() {
		this.timestamp = System.currentTimeMillis();
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	
	@Override
	public String toString() {
		return "OTPBean [uid=" + uid + ", oid=" + oid + ", channelName=" + channelName + ", otp="+otp;
	}
	

}
