package com.quest.vms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.vms.customexception.InternalServerError;
import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dao.IUserDao;
import com.quest.vms.dto.ContactPersonDto;
import com.quest.vms.dto.DeviceDto;
import com.quest.vms.dto.TimeSlotDto;
import com.quest.vms.dto.VisitorDto;
import com.quest.vms.entity.ContactPerson;
import com.quest.vms.entity.Device;
import com.quest.vms.entity.TimeSlot;
import com.quest.vms.entity.Visitor;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	IUserDao userDao;

	@Override
	public VisitorDto create(VisitorDto userDto)throws InternalServerError {


		ModelMapper modelMapper = new ModelMapper();
		Visitor user = modelMapper.map(userDto, Visitor.class);
//		User user = new User();
//		user.setFirstName(userDto.getFirstName());
//		user.setLastName(userDto.getLastName());
//		user.setEmail(userDto.getEmail());
//		user.setContactNo(userDto.getContactNo());
//		user.setIdProof(userDto.getIdProof());
//		user.setVisitorType(userDto.getVisitorType());
//		user.setReasonForVisit(userDto.getReasonForVisit());
//		user.setContactPerson(userDto.getContactPerson());
//		user.setTimeSlot(userDto.getTimeSlot());
//		user.setDevice(userDto.getDevice());
		
		
		if(userDao.save(user)==null)
			throw new InternalServerError("Error While saving data");
		
		return userDto;
	}

	@Override
	public VisitorDto getUserById(long id) throws RecordNotFoundException {
	
		Visitor user = userDao.getUserById(id);
		if(user==null)
			throw new RecordNotFoundException("Record not found with given ID");
		
		
		ModelMapper modelMapper = new ModelMapper();
		VisitorDto userDto = modelMapper.map(user, VisitorDto.class);
		
//		UserDto userDto = new UserDto();
//		userDto.setFirstName(user.getFirstName());
//		userDto.setLastName(user.getLastName());
//		userDto.setEmail(user.getEmail());
//		userDto.setContactNo(user.getContactNo());
//		userDto.setIdProof(user.getIdProof());
//		userDto.setVisitorType(user.getVisitorType());
//		userDto.setReasonForVisit(user.getReasonForVisit());
//		userDto.setContactPerson(user.getContactPerson());
//		userDto.setTimeSlot(user.getTimeSlot());
//		userDto.setDevice(user.getDevice());
		
		return userDto;
	}



	@Override
	public void delete(long id) throws RecordNotFoundException{
		Visitor userToBeDeleted = null;
		
			userToBeDeleted = userDao.getUserById(id);
			
			if (userToBeDeleted == null)
				throw new RecordNotFoundException("Record not found with id: " + id);
			
			userDao.delete(userToBeDeleted);	
	}
	
}
