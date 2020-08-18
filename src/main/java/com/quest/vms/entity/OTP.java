package com.quest.vms.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "otp")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OTP {

	public static final String ID = "id";
	public static final String OTP_NUMBER = "otpNumber";
	public static final String EMAIL = "email";
	public static final String TIMESTAMP = "timestamp";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ID)
	private Integer id;

	@Column(name = OTP_NUMBER, nullable = false)
	private Integer otpNumber;
	
	@Column(name = EMAIL, nullable = false)
	private String email;
	
	@CreationTimestamp
    @Column(name = TIMESTAMP)
	private Timestamp timestamp;
}
