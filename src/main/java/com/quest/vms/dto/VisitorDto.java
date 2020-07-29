package com.quest.vms.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)

public class VisitorDto {
	
	@JsonIgnore
	private long id;
	private long contactNo;
	private String email;
	private String firstName;
	private String lastName;	
	private String idProof;
	private String placeOfVisit;
	private String reasonForVisit;
	private String visitorType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdOn;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updatedOn;
	
	private List<ContactPersonDto> contactPerson;
	private List<TimeSlotDto> timeSlot;
	private List<DeviceDto> device;
	private List<VisitorMappingsDto> visitorMappings;

}
