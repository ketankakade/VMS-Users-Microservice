package com.quest.vms.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.quest.vms.dto.ContactPersonDto;
import com.quest.vms.dto.DeviceDto;
import com.quest.vms.dto.TimeSlotDto;
import com.quest.vms.dto.VisitorDto;
import com.quest.vms.entity.ContactPerson;
import com.quest.vms.entity.Device;
import com.quest.vms.entity.TimeSlot;
import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.VisitorRepository;

@Service
public class VisitorDao implements IVisitorDao {

	@Autowired
	private VisitorRepository visotorRepository;

	@Override
	public VisitorDto addVisitor(final VisitorDto visitorDto) {
		Visitor visitor = transformDtoToEntity(visitorDto);
		visitor = visotorRepository.save(visitor);
		return transformEntityToDto(visitor);
	}

	@Override
	public void delete(final Visitor visitor) {
		visotorRepository.delete(visitor);
	}

	@Override
	public VisitorDto getVisitorById(final Integer id) {
		VisitorDto visitorDto = null;
		Optional<Visitor> visitorOptional = visotorRepository.findById(id);
		if (visitorOptional.isPresent()) {
			Visitor visitor = visitorOptional.get();
			visitorDto = transformEntityToDto(visitor);
		}
		return visitorDto;
	}

	@Override
	public List<VisitorDto> listVisitors(Integer pageNo, Integer pageSize) {
		List<VisitorDto> visitorDTOList = new ArrayList<>();
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Visitor> pagedResult = visotorRepository.findAll(paging);
		List<Visitor> listedVisitors = pagedResult.toList();
		for (Visitor visitor : listedVisitors) {
			VisitorDto visitorDTO = transformEntityToDto(visitor);
			visitorDTOList.add(visitorDTO);
		}
		return visitorDTOList;
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
				.firstName(visitorDto.getFirstName()).lastName(visitorDto.getLastName())
				.idProof(visitorDto.getIdProof()).reasonForVisit(visitorDto.getReasonForVisit())
				.placeOfVisit(visitorDto.getPlaceOfVisit()).createdOn(LocalDateTime.now())
				.contactPersons(contactPersonSet).devices(deviceSet).timeSlots(timeslotSet).build();
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
