package com.quest.vms.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import com.quest.vms.dto.OtpDTO;
import com.quest.vms.dto.ValidateOtpDTO;
import com.quest.vms.dto.VisitorDTO;
import com.quest.vms.dto.VisitorsCountDTO;
import com.quest.vms.entity.OTP;
import com.quest.vms.entity.Visit;
import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.OtpRepository;
import com.quest.vms.repository.VisitorRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VisitorDAOImpl implements VisitorDAO {

	@Autowired
	private VisitorRepository visitorRepository;

	@Autowired
	private OtpRepository otpRepository;

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
		Pageable paging = PageRequest.of(Integer.parseInt(pageNo), Integer.parseInt(pageSize),
				Sort.by(orderBy, sortProperty));
		Page<Visitor> pagedResult = visitorRepository.findAll(paging);
		List<Visitor> listedVisitors = pagedResult.toList();
		log.info("listedVisitors size " + listedVisitors.size());
		for (Visitor visitor : listedVisitors) {
			VisitorDTO visitorDTO = transformEntityToDto(visitor);
			visitorDTOList.add(visitorDTO);
		}
		return visitorDTOList;
	}

	@Override
	public VisitorsCountDTO listVisitorsCount() {
		List<Visitor> approved = new ArrayList<Visitor>();
		List<Visitor> pending = new ArrayList<Visitor>();
		VisitorsCountDTO visitorCountDTO = new VisitorsCountDTO();
		List<Visitor> visitorList = visitorRepository.findTodaysVisitor();
		log.info("visitorList " + visitorList.size());
		// find by todays date
		visitorCountDTO.setTotalVisitorsVisitedTodayCount(visitorList.size());
		for (Visitor visitor : visitorList) {
			for (Visit v : visitor.getVisits()) {
				log.info("type == " + visitor.getVisitorType());
				if (v.getApprovalStatus().equalsIgnoreCase("approved")) {
					approved.add(visitor);
				} else {
					pending.add(visitor);
				}
			}

		}
		// if visits.approved add in list and get the size
		visitorCountDTO.setTotalVisitorsApprovedTodayCount(approved.size());
		// if visits.notApproved add in list and get the size
		visitorCountDTO.setTotalVisitorsNotApprovedTodayCount(pending.size());
		return visitorCountDTO;
	}

	@Override
	public List<VisitorDTO> searchVisitor(String visitorType, String startDate, String endDate, String visitorName,
			String contactPersonName, String isActive) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate st = null;
		LocalDate et = null;
		if (!startDate.equals("")) {
			st = LocalDate.parse(startDate, formatter);
			log.info("startDate " + st);
		}
		if (!endDate.equals("")) {
			et = LocalDate.parse(endDate, formatter);
			log.info("endDate " + et);
		}
		List<VisitorDTO> visitorDTOList = new ArrayList<>();
		List<Visitor> listedVisitors = visitorRepository.findByFilter(visitorName, visitorType, contactPersonName);
		for (Visitor visitor : listedVisitors) {
			VisitorDTO visitorDTO = transformEntityToDto(visitor);
			visitorDTOList.add(visitorDTO);
		}
		return visitorDTOList;
	}

	@Override
	public OtpDTO generateOtp(final OtpDTO OtpDto) {
		log.info("Save otp details::Visitor: {}", OtpDto);
		OTP otp = transformDtoToEntity(OtpDto);
		otp = otpRepository.save(otp);
		return transformEntityToDto(otp);
	}

	@Override
	public OTP validateOtp(ValidateOtpDTO validateOtpDTO) {
		return otpRepository.findFirstByOrderByTimestampDesc();
}

	public Visitor transformDtoToEntity(VisitorDTO dto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(dto, Visitor.class);
	}

	public VisitorDTO transformEntityToDto(Visitor entity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(entity, VisitorDTO.class);
	}

	public OTP transformDtoToEntity(OtpDTO dto) {
		return modelMapper.map(dto, OTP.class);
	}

	public OtpDTO transformEntityToDto(OTP entity) {
		return modelMapper.map(entity, OtpDTO.class);
	}

}
