package com.quest.vms.service;

import java.util.HashSet;
import java.util.Set;

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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService {

	@Autowired
	IUserDao userDao;

	@Override
	public VisitorDto create(final VisitorDto userDto) throws InternalServerError {
		log.info("save user");
		Set<ContactPerson> cpSet = new HashSet<ContactPerson>();
		Set<Device> dSet = new HashSet<Device>();
		Set<TimeSlot> tSet = new HashSet<TimeSlot>();
		for (ContactPersonDto p : userDto.getContactPerson()) {
			ContactPerson cp = new ContactPerson();
			cp.setContactNo(p.getContactNo());
			cp.setEmail(p.getEmail());
			cp.setFirstName(p.getFirstName());
			cp.setLastName(p.getLastName());
			cpSet.add(cp);
		}

		for (DeviceDto d : userDto.getDevice()) {
			Device device = new Device();
			device.setDeviceMake(d.getDeviceMake());
			device.setDeviceSN(d.getDeviceSN());
			device.setDeviceType(d.getDeviceType());
			dSet.add(device);
		}

		for (TimeSlotDto t : userDto.getTimeSlot()) {
			TimeSlot ts = new TimeSlot();
			ts.setEndtime(t.getEndTime());
			ts.setStartTime(t.getStartTime());
			tSet.add(ts);
		}

		Visitor visitor = new Visitor();
		visitor.setEmail(userDto.getEmail());
		visitor.setContactNo(userDto.getContactNo());
		visitor.setFirstName(userDto.getFirstName());
		visitor.setReasonForVisit(userDto.getReasonForVisit());
		visitor.setContactPersons(cpSet);
		visitor.setDevices(dSet);
		visitor.setTimeSlots(tSet);

		if (userDao.save(visitor) == null)
			throw new InternalServerError("Error While saving data");
		return userDto;
	}

	@Override
	public VisitorDto getUserById(long id) throws RecordNotFoundException {
		Visitor user = userDao.getUserById(id);
		if (user == null)
			throw new RecordNotFoundException("Record not found with given ID");
		ModelMapper modelMapper = new ModelMapper();
		VisitorDto userDto = modelMapper.map(user, VisitorDto.class);
		return userDto;
	}

	@Override
	public void delete(long id) throws RecordNotFoundException {
		Visitor userToBeDeleted = null;
		userToBeDeleted = userDao.getUserById(id);
		if (userToBeDeleted == null)
			throw new RecordNotFoundException("Record not found with id: " + id);
		userDao.delete(userToBeDeleted);
	}

}
