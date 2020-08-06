package com.quest.vms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {
	
	@JsonIgnore
	private Integer deviceId;	
	private String serialNumber;
	private String deviceType;
	private String deviceMake;
	private Boolean isSecurityCheckDone;
	private String securityFlaws;
}
