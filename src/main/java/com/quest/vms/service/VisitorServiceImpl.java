package com.quest.vms.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.quest.vms.common.utils.ErrorCodes;
import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dao.VisitorDAO;
import com.quest.vms.dto.VisitorDTO;
import com.quest.vms.entity.Visitor;
import com.quest.vms.repository.VisitorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VisitorServiceImpl implements VisitorService {

	@Autowired
	private VisitorDAO visitorDao;

	@Autowired
	private VisitorRepository visitorRepository;

	@Override
	public GenericResponse<VisitorDTO> addVisitor(final VisitorDTO visitorDto) {
		GenericResponse<VisitorDTO> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		log.info("save visitor");
		if (visitorDto != null) {
			VisitorDTO visitor = visitorDao.addVisitor(visitorDto);
			genericResponse.setStatusCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(Collections.singletonList(visitor));
		} else {
			genericResponse.setMessage("Failed to the persist Visitor");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<VisitorDTO> getVisitorById(final Integer visitorId) {
		GenericResponse<VisitorDTO> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		VisitorDTO visitorDTO = visitorDao.getVisitorById(visitorId);
		if (visitorDTO != null) {
			genericResponse.setStatusCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(Collections.singletonList(visitorDTO));
		} else {
			genericResponse.setMessage("Visitor not found");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<VisitorDTO> listVisitors(final String pageNo, final String pageSize, final String sortProperty, Sort.Direction orderBy) {
		GenericResponse<VisitorDTO> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		List<VisitorDTO> listedVisitors = visitorDao.listVisitors(pageNo, pageSize, sortProperty, orderBy);
		if (!listedVisitors.isEmpty()) {
			genericResponse.setStatusCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
			genericResponse.setData(listedVisitors);
		} else {
			genericResponse.setMessage("Visitor List is empty");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<?> deleteVisitor(final Integer visitorId) {
		GenericResponse<?> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE, "BAD_REQUEST",
				null, null);
		Optional<Visitor> visitorToBeDeleted = visitorRepository.findById(visitorId);
		if (visitorToBeDeleted == null) {
			genericResponse.setMessage("Delete visitor failed..");
			return genericResponse;
		} else {
			Visitor visitor = visitorToBeDeleted.get();
			visitorDao.delete(visitor);
			genericResponse.setStatusCode(HttpStatus.OK.value());
			genericResponse.setMessage("Success");
		}
		return genericResponse;
	}

	@Override
	public GenericResponse<VisitorDTO> updateVisitor(VisitorDTO visitorDto) {
		GenericResponse<VisitorDTO> genericResponse = new GenericResponse<>(ErrorCodes.BAD_REQUEST_STATUS_CODE,
				"BAD_REQUEST", null, null);
		if (visitorDto == null) {
			genericResponse.setMessage("visitor data is missing in request");
		} else {
			VisitorDTO visitor = visitorDao.update(visitorDto);
			if (visitor == null) {
				genericResponse.setStatusCode(HttpStatus.OK.value());
				genericResponse.setMessage("Success");
				genericResponse.setData(Collections.singletonList(visitor));
			} else {
				genericResponse.setMessage("visitor is not found");
			}
		}
		return genericResponse;
	}

}
