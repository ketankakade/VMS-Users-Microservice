package com.quest.vms.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class VisitorDTO {

	private Integer visitorId;
	private long contactNo;
	private String email;
	private String firstName;
	private String lastName;
	private String idProof;
	private String placeOfVisit;
	private String reasonForVisit;
	private String visitorType;
	private List<VisitDTO> visits;

}
