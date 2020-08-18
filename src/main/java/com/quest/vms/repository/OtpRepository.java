package com.quest.vms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.vms.entity.OTP;
import com.quest.vms.entity.Visitor;

public interface OtpRepository 
extends JpaRepository<OTP, Integer> {
	public OTP findByEmailIgnoreCase(String email);
	

}
