package com.quest.vms.service;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.VisitorDto;

public interface IVisitorService {

	public GenericResponse<VisitorDto> addVisitor(VisitorDto visitor);

	public GenericResponse<VisitorDto> getVisitorById(Integer id);

	public GenericResponse<?> deleteVisitor(Integer id);

	public GenericResponse<VisitorDto> listVisitors(Integer pageNo, Integer pageSize);
}
