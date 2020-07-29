package com.quest.vms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DeviceDto {
	
	@JsonIgnore
	private int id;	
	private String deviceSN;
	private String deviceType;
	private String deviceMake;
}
