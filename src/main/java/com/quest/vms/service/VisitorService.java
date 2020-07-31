package com.quest.vms.service;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.vms.customexception.InternalServerError;
import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dao.IVisitorDao;
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
public class VisitorService implements IVisitorService {

	@Autowired
	IVisitorDao visitorDao;

	@Override
	public VisitorDto create(final VisitorDto visitorDto) throws InternalServerError {
		log.info("save visitor");
		Set<ContactPerson> contactPersonSet = new HashSet<ContactPerson>();
		Set<Device> deviceSet = new HashSet<Device>();
		Set<TimeSlot> timeslotSet = new HashSet<TimeSlot>();
		for (ContactPersonDto p : visitorDto.getContactPerson()) {
			ContactPerson cp = new ContactPerson();
			cp.setContactNo(p.getContactNo());
			cp.setEmail(p.getEmail());
			cp.setFirstName(p.getFirstName());
			cp.setLastName(p.getLastName());
			contactPersonSet.add(cp);
		}

		for (DeviceDto d : visitorDto.getDevice()) {
			Device device = new Device();
			device.setDeviceMake(d.getDeviceMake());
			device.setDeviceSN(d.getDeviceSN());
			device.setDeviceType(d.getDeviceType());
			deviceSet.add(device);
		}

		for (TimeSlotDto t : visitorDto.getTimeSlot()) {
			TimeSlot ts = new TimeSlot();
			ts.setEndtime(t.getEndTime());
			ts.setStartTime(t.getStartTime());
			timeslotSet.add(ts);
		}

		Visitor visitor = new Visitor();
		visitor.setEmail(visitorDto.getEmail());
		visitor.setContactNo(visitorDto.getContactNo());
		visitor.setFirstName(visitorDto.getFirstName());
		visitor.setReasonForVisit(visitorDto.getReasonForVisit());
		visitor.setContactPersons(contactPersonSet);
		visitor.setDevices(deviceSet);
		visitor.setTimeSlots(timeslotSet);

		if (visitorDao.save(visitor) == null)
			throw new InternalServerError("Error While saving data");
		return visitorDto;
	}

	@Override
	public VisitorDto getvisitorById(long id) throws RecordNotFoundException {
		Visitor visitor = visitorDao.getVisitorById(id);
		if (visitor == null)
			throw new RecordNotFoundException("Record not found with given ID");
		ModelMapper modelMapper = new ModelMapper();
		VisitorDto userDto = modelMapper.map(visitor, VisitorDto.class);
		return userDto;
	}

	@Override
	public void delete(long id) throws RecordNotFoundException {
		Visitor visitorToBeDeleted = null;
		visitorToBeDeleted = visitorDao.getVisitorById(id);
		if (visitorToBeDeleted == null)
			throw new RecordNotFoundException("Record not found with id: " + id);
		visitorDao.delete(visitorToBeDeleted);
	}

}
