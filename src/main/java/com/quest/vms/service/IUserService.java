package com.quest.vms.service;

import com.quest.vms.customexception.InternalServerError;

import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dto.VisitorDto;

public interface IUserService {

	public VisitorDto create(VisitorDto user) throws InternalServerError;
	public VisitorDto getUserById(long id) throws RecordNotFoundException;
	public void delete(long id) throws RecordNotFoundException;
}
