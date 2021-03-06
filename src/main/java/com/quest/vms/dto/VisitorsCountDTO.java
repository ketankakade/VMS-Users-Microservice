package com.quest.vms.dto;

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
public class VisitorsCountDTO {

	private Integer totalVisitorsVisitedTodayCount;
	private Integer totalVisitorsApprovedTodayCount;
	private Integer totalVisitorsNotApprovedTodayCount;

}
