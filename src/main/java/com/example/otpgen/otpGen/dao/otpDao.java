package com.example.otpgen.otpGen.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.otpgen.otpGen.entities.OTPBean;

public interface otpDao extends JpaRepository<OTPBean,String>{

}
