package com.quest.vms.service;

import org.springframework.data.domain.Sort.Direction;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.VisitorDTO;
import com.quest.vms.dto.VisitorsCountDTO;

public interface VisitorService {

	public GenericResponse<VisitorDTO> addVisitor(final VisitorDTO visitor);

	public GenericResponse<VisitorDTO> getVisitorById(final Integer id);

	public GenericResponse<?> deleteVisitor(final Integer id);

	public GenericResponse<VisitorDTO> updateVisitor(VisitorDTO visitor);

	public GenericResponse<VisitorDTO> listVisitors(String index, String size, String sortBy, Direction orderBy);
	
	public GenericResponse<VisitorsCountDTO> listVisitorsCount();

	public GenericResponse<VisitorDTO> searchVisitor(String visitorType, String startDate, String endDate,
			String visitorName, String contactPersonName, String isActive);
	
}
