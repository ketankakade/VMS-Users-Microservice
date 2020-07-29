package com.quest.vms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.quest.vms.entity.ContactPerson;
import com.quest.vms.entity.Device;
import com.quest.vms.entity.TimeSlot;
import com.quest.vms.entity.Visitor;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)

public class VisitorMappingsDto {
	
	@JsonIgnore
	private long id;
	
	private Visitor visitorId;
	
	private Device deviceId;
	
	private TimeSlot timeslotId;
	
	private ContactPerson contactPersonId;

}
