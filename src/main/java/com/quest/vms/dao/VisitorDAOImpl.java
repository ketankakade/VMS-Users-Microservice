package com.quest.vms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.quest.vms.dto.VisitorDTO;
import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.VisitorRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VisitorDAOImpl implements VisitorDAO {

	@Autowired
	private VisitorRepository visitorRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public VisitorDTO addVisitor(final VisitorDTO visitorDto) {
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
	public VisitorDTO update(VisitorDTO visitorDto) {
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
	public VisitorDTO getVisitorById(final Integer id) {
		VisitorDTO visitorDto = null;
		Optional<Visitor> visitorOptional = visitorRepository.findById(id);
		if (visitorOptional.isPresent()) {
			Visitor visitor = visitorOptional.get();
			visitorDto = transformEntityToDto(visitor);
		}
		return visitorDto;
	}

	@Override
	public List<VisitorDTO> listVisitors(String pageNo, String pageSize, String sortProperty, Sort.Direction orderBy) {
		List<VisitorDTO> visitorDTOList = new ArrayList<>();
		Pageable paging = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Sort.by(orderBy, sortProperty));
		Page<Visitor> pagedResult = visitorRepository.findAll(paging);
		List<Visitor> listedVisitors = pagedResult.toList();
		log.info("listedVisitors size " + listedVisitors.size());
		for (Visitor visitor : listedVisitors) {
			VisitorDTO visitorDTO = transformEntityToDto(visitor);
			visitorDTOList.add(visitorDTO);
		}
		return visitorDTOList;
	}

	public Visitor transformDtoToEntity(VisitorDTO dto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(dto, Visitor.class);
	}

	public VisitorDTO transformEntityToDto(Visitor entity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(entity, VisitorDTO.class);
	}

}
