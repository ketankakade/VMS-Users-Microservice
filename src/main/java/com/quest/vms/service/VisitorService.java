package com.quest.vms.service;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.VisitorDTO;

public interface VisitorService {

	public GenericResponse<VisitorDTO> addVisitor(final VisitorDTO visitor);

	public GenericResponse<VisitorDTO> getVisitorById(final Integer id);

	public GenericResponse<?> deleteVisitor(final Integer id);

	public GenericResponse<VisitorDTO> updateVisitor(VisitorDTO visitor);

	public GenericResponse<VisitorDTO> listVisitors(final String pageNo, final String pageSize, final String sortProperty);
}
