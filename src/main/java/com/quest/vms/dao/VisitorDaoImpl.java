package com.quest.vms.dao;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public VisitorDto addVisitor(final VisitorDto visitorDto) {
		log.info("Save visitor details::Visitor: {}", visitorDto);
		Visitor visitor = transformDtoToEntity(visitorDto);
		visitor = visitorRepository.save(visitor);
		return transformEntityToDto(visitor);
	}

	@Override
	public void delete(final Visitor visitor) {
		log.info("Delete visitor details::Visitor: {}", visitor.getVisitorId());
		visitorRepository.delete(visitor);
	}

	@Override
	public VisitorDto update(VisitorDto visitorDto) {
		log.info("Update visitor details::Visitor: {}", visitorDto.getVisitorId());
		Optional<Visitor> visitor = visitorRepository.findById(visitorDto.getVisitorId());
		if (visitor == null) {
			return null;
		} else {
		Visitor visitorToBeUpdated = transformDtoToEntity(visitorDto);
		visitorToBeUpdated.setVisitorId(visitorDto.getVisitorId());
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
	
	public Visitor transformDtoToEntity(VisitorDto dto) {
	    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    return modelMapper.map(dto, Visitor.class);
	  }
	  
	  public VisitorDto transformEntityToDto(Visitor entity) {
	    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	    return modelMapper.map(entity, VisitorDto.class);
	  }

	}
