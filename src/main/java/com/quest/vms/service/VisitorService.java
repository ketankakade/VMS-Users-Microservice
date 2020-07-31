package com.quest.vms.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quest.vms.common.utils.ErrorCodes;
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
		Visitor visitor = transformDtoToEntity(visitorDto);
		if (visitorDao.save(visitor) == null)
			throw new InternalServerError("Error While saving data");
		return visitorDto;
	}

	@Override
	public GenericResponse<VisitorDto> getVisitorById(Integer visitorId) {
		GenericResponse<VisitorDto> genericRes = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		Visitor visitor = visitorDao.getVisitorById(visitorId);
		if (visitor != null) {
			VisitorDto visitorDTO = transformEntityToDto(visitor);
			genericRes.setMessageCode(HttpStatus.OK.value());
			genericRes.setMessage("Success");
			genericRes.setData(Collections.singletonList(visitorDTO));
		} else {
			genericRes.setErrorCode("400");
		}
		return genericRes;
	}

	@Override
	public GenericResponse<VisitorDto> listVisitors() {
		GenericResponse<VisitorDto> genericRes = new GenericResponse<VisitorDto>();
		List<VisitorDto> visitorDTOList = new ArrayList<>();
		List<Visitor> listedVisitors = visitorDao.listVisitors();
		if (!listedVisitors.isEmpty()) {
			for (Visitor vis : listedVisitors) {
				VisitorDto visitorDTO = transformEntityToDto(vis);
				visitorDTOList.add(visitorDTO);
			}
			genericRes.setMessageCode(HttpStatus.OK.value());
			genericRes.setMessage("Success");
			genericRes.setData(visitorDTOList);
		} else {
			genericRes.setErrorCode("400");
			genericRes.setMessage("get visitor list failed");
			return genericRes;
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

	public Visitor transformDtoToEntity(VisitorDto visitorDto) {
		Set<ContactPerson> contactPersonSet = new HashSet<ContactPerson>();
		Set<Device> deviceSet = new HashSet<Device>();
		Set<TimeSlot> timeslotSet = new HashSet<TimeSlot>();
		for (ContactPersonDto p : visitorDto.getContactPerson()) {
			ContactPerson cp = ContactPerson.builder().contactNo(p.getContactNo()).email(p.getEmail())
					.firstName(p.getFirstName()).lastName(p.getLastName()).build();
			contactPersonSet.add(cp);
		}
		for (DeviceDto d : visitorDto.getDevice()) {
			Device device = Device.builder().deviceMake(d.getDeviceMake()).deviceSN(d.getDeviceSN())
					.deviceType(d.getDeviceType()).build();
			deviceSet.add(device);
		}
		for (TimeSlotDto t : visitorDto.getTimeSlot()) {
			TimeSlot ts = TimeSlot.builder().endtime(t.getEndTime()).startTime(t.getStartTime()).build();
			timeslotSet.add(ts);
		}
		Visitor visitor = Visitor.builder().email(visitorDto.getEmail()).contactNo(visitorDto.getContactNo())
				.firstName(visitorDto.getFirstName())
				.lastName(visitorDto.getLastName())
				.idProof(visitorDto.getIdProof())
				.reasonForVisit(visitorDto.getReasonForVisit())
				.placeOfVisit(visitorDto.getPlaceOfVisit())
				.createdOn(LocalDateTime.now())
				.contactPersons(contactPersonSet).devices(deviceSet).timeSlots(timeslotSet)
				.build();
		return visitor;
	}

	public VisitorDto transformEntityToDto(Visitor entity) {
		List<ContactPersonDto> contactPersonDtoList = new ArrayList<>();
		List<DeviceDto> deviceDtoList = new ArrayList<>();
		List<TimeSlotDto> tomeSlotDtoList = new ArrayList<>();
		for (ContactPerson cp : entity.getContactPersons()) {
			ContactPersonDto contactPersonDto = ContactPersonDto.builder().contactPersonId(cp.getContactpersonid())
					.email(cp.getEmail()).firstName(cp.getFirstName()).lastName(cp.getLastName())
					.contactNo(cp.getContactNo()).build();
			contactPersonDtoList.add(contactPersonDto);
		}
		for (Device dev : entity.getDevices()) {
			DeviceDto deviceDto = DeviceDto.builder().id(dev.getDeviceId()).deviceMake(dev.getDeviceMake())
					.deviceSN(dev.getDeviceSN()).deviceType(dev.getDeviceType()).build();
			deviceDtoList.add(deviceDto);
		}
		for (TimeSlot ts : entity.getTimeSlots()) {
			TimeSlotDto slotDto = TimeSlotDto.builder().timeslotid(ts.getTimeslotId()).startTime(ts.getStartTime())
					.endTime(ts.getEndtime()).build();
			tomeSlotDtoList.add(slotDto);
		}
		VisitorDto dto = VisitorDto.builder().firstName(entity.getFirstName()).lastName(entity.getLastName())
				.email(entity.getEmail()).contactNo(entity.getContactNo()).idProof(entity.getIdProof())
				.placeOfVisit(entity.getPlaceOfVisit()).reasonForVisit(entity.getReasonForVisit())
				.contactPerson(contactPersonDtoList).device(deviceDtoList).timeSlot(tomeSlotDtoList).build();
		return dto;
	}

}
