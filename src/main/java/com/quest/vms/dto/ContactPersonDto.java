package com.quest.vms.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)

public class ContactPersonDto {
	
	@JsonIgnore
	private long contactPersonId;

	private String firstName;
	
	private String lastName;

	private long contactNo;
	
	private String email;

}
