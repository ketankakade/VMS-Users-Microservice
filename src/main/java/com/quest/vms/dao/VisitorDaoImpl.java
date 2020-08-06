package com.quest.vms.dao;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.quest.vms.dto.ContactPersonDto;
import com.quest.vms.dto.DeviceDto;
import com.quest.vms.dto.TimeSlotDto;
import com.quest.vms.dto.VisitDto;
import com.quest.vms.dto.VisitorDto;
import com.quest.vms.entity.ContactPerson;
import com.quest.vms.entity.Device;
import com.quest.vms.entity.TimeSlot;
import com.quest.vms.entity.Visit;
import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.VisitorRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VisitorDaoImpl implements VisitorDao {

	@Autowired
	private VisitorRepository visitorRepository;

	@Override
	public VisitorDto addVisitor(final VisitorDto visitorDto) {
		log.info("Save visitor details::Visitor: {}", visitorDto);
		Visitor visitor = transformDtoToEntity(visitorDto);
		visitor = visitorRepository.save(visitor);
		return transformEntityToDto(visitor);
	}

	@Override
	public void delete(final Visitor visitor) {
		log.info("Delete visitor details::Visitor: {}", visitor.getId());
		visitorRepository.delete(visitor);
	}

	@Override
	public VisitorDto update(VisitorDto visitorDto) {
		log.info("Update visitor details::Visitor: {}", visitorDto.getId());
		Optional<Visitor> visitor = visitorRepository.findById(visitorDto.getId());
		if (visitor == null) {
			return null;
		} else {
		Visitor visitorToBeUpdated = transformDtoToEntity(visitorDto);
		visitorToBeUpdated.setId(visitorDto.getId());
		visitorToBeUpdated.setCreatedTs(visitor.get().getCreatedTs());
		visitorToBeUpdated = visitorRepository.save(visitorToBeUpdated);
		return transformEntityToDto(visitorToBeUpdated);
		}
	}

	@Override
	public VisitorDto getVisitorById(final Integer id) {
		VisitorDto visitorDto = null;
		Optional<Visitor> visitorOptional = visitorRepository.findById(id);
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
		Page<Visitor> pagedResult = visitorRepository.findAll(paging);
		List<Visitor> listedVisitors = pagedResult.toList();
		log.info("listedVisitors size " + listedVisitors.size());
		for (Visitor visitor : listedVisitors) {
			VisitorDto visitorDTO = transformEntityToDto(visitor);
			visitorDTOList.add(visitorDTO);
		}
		return visitorDTOList;
	}

	public Visitor transformDtoToEntity(VisitorDto visitorDto) {
		List<Visit> visitSet = new ArrayList<>();

		for (VisitDto visitDto : visitorDto.getVisits()) {
			List<Device> deviceSet = new ArrayList<>();
			ContactPerson contactPerson = ContactPerson.builder().firstName(visitDto.getContactPerson().getFirstName())
					.lastName(visitDto.getContactPerson().getLastName())
					.contactNo(visitDto.getContactPerson().getContactNo()).email(visitDto.getContactPerson().getEmail())
					.build();
			TimeSlot timeSlot = TimeSlot.builder().startTime(LocalDateTime.now()).endtime(LocalDateTime.now()).build();

			for (DeviceDto deviceDto : visitDto.getDevice()) {
				Device device = Device.builder().deviceId(deviceDto.getId()).deviceMake(deviceDto.getDeviceMake())
						.deviceSN(deviceDto.getDeviceSN()).deviceType(deviceDto.getDeviceType()).isSecurityCheckDone(deviceDto.getIsSecurityCheckDone())
						.securityFlaws(deviceDto.getSecurityFlaws()).build();
				deviceSet.add(device);
			}
			Visit visit = Visit.builder().visitDate(new java.util.Date()).contactPerson(contactPerson)
					.timeSlot(timeSlot).devices(deviceSet).build();
			visitSet.add(visit);
		}

		Visitor visitor = Visitor.builder().email(visitorDto.getEmail()).contactNo(visitorDto.getContactNo())
				.firstName(visitorDto.getFirstName()).lastName(visitorDto.getLastName())
				.idProof(visitorDto.getIdProof()).reasonForVisit(visitorDto.getReasonForVisit())
				.placeOfVisit(visitorDto.getPlaceOfVisit()).visitorType(visitorDto.getVisitorType()).visits(visitSet).build();
		return visitor;
	}

	public VisitorDto transformEntityToDto(Visitor entity) {
		List<VisitDto> VisitDtoSet = new ArrayList<>();

		for (Visit visit : entity.getVisits()) {
			List<DeviceDto> deviceDtoSet = new ArrayList<>();
			ContactPersonDto contactPersonDto = ContactPersonDto.builder()
					.firstName(visit.getContactPerson().getFirstName()).lastName(visit.getContactPerson().getLastName())
					.contactNo(visit.getContactPerson().getContactNo()).email(visit.getContactPerson().getEmail())
					.build();
			TimeSlotDto timeSlotDto = TimeSlotDto.builder().startTime(LocalDateTime.now()).endTime(LocalDateTime.now())
					.build();

			for (Device device : visit.getDevices()) {
				DeviceDto deviceDto = DeviceDto.builder().id(device.getDeviceId()).deviceMake(device.getDeviceMake())
						.deviceSN(device.getDeviceSN()).deviceType(device.getDeviceType()).build();
				deviceDtoSet.add(deviceDto);
			}

			VisitDto visitDto = VisitDto.builder().visitDate(visit.getVisitDate()).contactPerson(contactPersonDto)
					.timeSlot(timeSlotDto).device(deviceDtoSet).build();
			VisitDtoSet.add(visitDto);
		}

		VisitorDto dto = VisitorDto.builder().firstName(entity.getFirstName()).lastName(entity.getLastName())
				.email(entity.getEmail()).contactNo(entity.getContactNo()).idProof(entity.getIdProof())
				.placeOfVisit(entity.getPlaceOfVisit()).reasonForVisit(entity.getReasonForVisit())
				.visitorType(entity.getVisitorType()).visits(VisitDtoSet).build();
		return dto;
	}
}
