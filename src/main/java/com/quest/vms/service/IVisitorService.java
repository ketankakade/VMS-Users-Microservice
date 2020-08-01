package com.quest.vms.service;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.customexception.InternalServerError;
import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dto.VisitorDto;

public interface IVisitorService {

	public VisitorDto create(VisitorDto visitor) throws InternalServerError;

	public GenericResponse<VisitorDto> getVisitorById(Integer id) throws RecordNotFoundException;

	public void delete(Integer id) throws RecordNotFoundException;

	public GenericResponse<VisitorDto> listVisitors(Integer pageNo, Integer pageSize);
}
