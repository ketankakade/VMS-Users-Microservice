package com.quest.vms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.vms.entity.OTP;

public interface OtpRepository extends JpaRepository<OTP, Integer> {
	
	public OTP findByEmailIgnoreCase(String email);
	
	OTP findFirstByEmailOrderByTimestampDesc(String email);
	
}
