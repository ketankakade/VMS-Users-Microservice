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
		GenericResponse<VisitorDto> genericRes = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		log.info("save visitor");
		if (visitorDto != null) {
			VisitorDto visitor = visitorDao.addVisitor(visitorDto);
			genericRes.setMessageCode(HttpStatus.OK.value());
			genericRes.setMessage("Success");
			genericRes.setData(Collections.singletonList(visitor));
		} else {
			genericRes.setMessage("Failed to the persist Visitor");
		}
		return genericRes;
	}

	@Override
	public GenericResponse<VisitorDto> getVisitorById(Integer visitorId) {
		GenericResponse<VisitorDto> genericRes = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		VisitorDto visitorDTO = visitorDao.getVisitorById(visitorId);
		if (visitorDTO != null) {
			genericRes.setMessageCode(HttpStatus.OK.value());
			genericRes.setMessage("Success");
			genericRes.setData(Collections.singletonList(visitorDTO));
		} else {
			genericRes.setMessage("Visitor not found");
		}
		return genericRes;
	}

	@Override
	public GenericResponse<VisitorDto> listVisitors(Integer pageNo, Integer pageSize) {
		GenericResponse<VisitorDto> genericRes = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		List<VisitorDto> listedVisitors = visitorDao.listVisitors(pageNo, pageSize);
		if (!listedVisitors.isEmpty()) {
			genericRes.setMessageCode(HttpStatus.OK.value());
			genericRes.setMessage("Success");
			genericRes.setData(listedVisitors);
		} else {
			genericRes.setMessage("Visitor List is empty");
		}
		return genericRes;
	}

	@Override
	public GenericResponse<?> deleteVisitor(Integer visitorId) {
		GenericResponse<?> genericRes = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE, "BAD_REQUEST",
				null, null);
		Optional<Visitor> visitorToBeDeleted = visitorRepository.findById(visitorId);
		if (visitorToBeDeleted == null) {
			genericRes.setMessage("Delete visitor failed..");
			return genericRes;
		} else {
			Visitor visitor = visitorToBeDeleted.get();
			visitorDao.delete(visitor);
			genericRes.setMessageCode(HttpStatus.OK.value());
			genericRes.setMessage("Success");
		}
		return genericRes;
	}

}
