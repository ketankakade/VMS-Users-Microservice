package com.quest.vms.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {

	@JsonIgnore
	private Integer visitId;
	private Date visitDate;
	private ContactPersonDto contactPerson;
	private TimeSlotDto timeSlot;
	private List<DeviceDto> devices;

}
