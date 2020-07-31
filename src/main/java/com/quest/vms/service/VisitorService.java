package com.quest.vms.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quest.vms.common.utils.GenericResponse;
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
	private ModelMapper modelMapper = new ModelMapper();

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
	public GenericResponse<VisitorDto> getVisitorById(Integer visitorId) {
		GenericResponse<VisitorDto> genericRes = new GenericResponse<VisitorDto>();
		Visitor visitor = visitorDao.getVisitorById(visitorId);
		if (visitor != null) {
			VisitorDto visitorDTO = transformEntityToDto(visitor);
			genericRes.setMessageCode(HttpStatus.OK.value());
			genericRes.setMessage("Success");
			genericRes.setData(Collections.singletonList(visitorDTO));
		} else {
			genericRes.setErrorCode("400");
			genericRes.setMessage("get visitor failed");
		}
		return genericRes;
	}

	@Override
	public GenericResponse<VisitorDto> listVisitors() {
		GenericResponse<VisitorDto> genericRes = new GenericResponse<VisitorDto>();
		List<VisitorDto> visitorDTOList = new ArrayList<>();
		List<Visitor> listedVisitors = visitorDao.listVisitors();
		if (listedVisitors.isEmpty()) {
			genericRes.setErrorCode("400");
			genericRes.setMessage("get visitor list failed");
		} else {
			for (Visitor vis : listedVisitors) {
				VisitorDto visitorDTO = transformEntityToDto(vis);
				visitorDTOList.add(visitorDTO);
			}
			genericRes.setMessageCode(HttpStatus.OK.value());
			genericRes.setMessage("Success");
			genericRes.setData(visitorDTOList);
		}
		return genericRes;
	}

	@Override
	public void delete(Integer id) throws RecordNotFoundException {
		Visitor visitorToBeDeleted = null;
		visitorToBeDeleted = visitorDao.getVisitorById(id);
		if (visitorToBeDeleted == null)
			throw new RecordNotFoundException("Record not found with id: " + id);
		visitorDao.delete(visitorToBeDeleted);
	}

	public Visitor transformDtoToEntity(VisitorDto dto) {
		return modelMapper.map(dto, Visitor.class);
	}

	public VisitorDto transformEntityToDto(Visitor entity) {
		return modelMapper.map(entity, VisitorDto.class);
	}

}
