package com.quest.vms.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quest.vms.common.utils.ErrorCodes;
import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dao.IVisitorDao;
import com.quest.vms.dto.VisitorDto;
import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.VisitorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VisitorService implements IVisitorService {

	@Autowired
	private IVisitorDao visitorDao;

	@Autowired
	private VisitorRepository visitorRepository;

	@Override
	public GenericResponse<VisitorDto> addVisitor(final VisitorDto visitorDto) {
		GenericResponse<VisitorDto> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		log.info("save visitor");
		if (visitorDto != null) {
			VisitorDto visitor = visitorDao.addVisitor(visitorDto);
			genericResponse.setMessageCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(Collections.singletonList(visitor));
		} else {
			genericResponse.setMessage("Failed to the persist Visitor");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<VisitorDto> getVisitorById(final Integer visitorId) {
		GenericResponse<VisitorDto> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		VisitorDto visitorDTO = visitorDao.getVisitorById(visitorId);
		if (visitorDTO != null) {
			genericResponse.setMessageCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(Collections.singletonList(visitorDTO));
		} else {
			genericResponse.setMessage("Visitor not found");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<VisitorDto> listVisitors(final Integer pageNo, final Integer pageSize) {
		GenericResponse<VisitorDto> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		List<VisitorDto> listedVisitors = visitorDao.listVisitors(pageNo, pageSize);
		if (!listedVisitors.isEmpty()) {
			genericResponse.setMessageCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(listedVisitors);
		} else {
			genericResponse.setMessage("Visitor List is empty");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<?> deleteVisitor(Integer visitorId) {
		GenericResponse<?> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE, "BAD_REQUEST", null,
				null);
		Optional<Visitor> visitorToBeDeleted = visitorRepository.findById(visitorId);
		if (visitorToBeDeleted == null) {
			genericResponse.setMessage("Delete visitor failed..");
			return genericResponse;
		} else {
			Visitor visitor = visitorToBeDeleted.get();
			visitorDao.delete(visitor);
			genericResponse.setMessageCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
		}
		return genericResponse;
	}

}
