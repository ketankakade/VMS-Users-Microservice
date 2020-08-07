package com.quest.vms.service;

import org.springframework.data.domain.Sort;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.VisitorDto;

public interface VisitorService {

	public GenericResponse<VisitorDto> addVisitor(final VisitorDto visitor);

	public GenericResponse<VisitorDto> getVisitorById(final Integer id);

	public GenericResponse<?> deleteVisitor(final Integer id);
	
	public GenericResponse<VisitorDto> updateVisitor(VisitorDto visitor);

	public GenericResponse<VisitorDto> listVisitors(final String pageNo, final String pageSize, String sortProperty);
}
