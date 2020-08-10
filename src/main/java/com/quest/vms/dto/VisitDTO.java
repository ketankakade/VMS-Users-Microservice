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
public class VisitDTO {

	@JsonIgnore
	private Integer visitId;
	private Date visitDate;
	private ContactPersonDTO contactPerson;
	private TimeSlotDTO timeSlot;
	private List<DeviceDTO> devices;

}
