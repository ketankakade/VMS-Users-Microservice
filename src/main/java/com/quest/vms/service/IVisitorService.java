package com.quest.vms.service;

import com.quest.vms.customexception.InternalServerError;

import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dto.VisitorDto;

public interface IVisitorService {

	public VisitorDto create(VisitorDto visitor) throws InternalServerError;
	public VisitorDto getvisitorById(long id) throws RecordNotFoundException;
	public void delete(long id) throws RecordNotFoundException;
}
