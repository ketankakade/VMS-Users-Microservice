package com.quest.vms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "devices")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
	
	public static final String DEVICE_ID = "device_id";
	public static final String SERIAL_NUMBER = "serial_number";
	public static final String DEVICE_TYPE = "device_type";
	public static final String DEVICE_MAKE = "device_make";
	public static final String IS_CHECK_DONE = "is_security_check_done";
	public static final String SECURITY_FLAWS = "security_flaws";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DEVICE_ID)
	private Integer deviceId;

	@Column(name = SERIAL_NUMBER)
	private String serialNumber;

	@Column(name = DEVICE_TYPE)
	private String deviceType;

	@Column(name = DEVICE_MAKE)
	private String deviceMake;
	
	@Column(name = IS_CHECK_DONE)
	private Boolean isSecurityCheckDone;
	
	@Column(name = SECURITY_FLAWS)
	private String securityFlaws;

}
