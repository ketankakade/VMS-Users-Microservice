package com.quest.vms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.quest.vms.entity.ContactPerson;
import com.quest.vms.entity.Visitor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class ContactPersonMappingDto {
	
	
	private Visitor visitorId;	
	
	private ContactPerson contactPersonId;



}
