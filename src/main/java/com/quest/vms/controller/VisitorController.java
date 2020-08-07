package com.quest.vms.controller;

import static com.quest.vms.common.utils.VmsConstants.CREATE_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.DELETE_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.GET_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.ID;
import static com.quest.vms.common.utils.VmsConstants.LIST_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.UPDATE_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.VISITOR_URL_PATH;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.VisitorDto;
import com.quest.vms.service.VisitorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/" + VISITOR_URL_PATH)
@Api(value = "Visitor Management System", description = "Operations pertaining to Visitor Management System")
@Slf4j
public class VisitorController {

	@Autowired
	private VisitorService visitorService;

	@ApiOperation(value = "Add a Visitor to system")
	@PostMapping(CREATE_VISITOR)
	public GenericResponse<VisitorDto> addVisitor(@Valid @RequestBody VisitorDto visitor) {
		GenericResponse<VisitorDto> createVisitorGenericResponse = null;
		try {
			createVisitorGenericResponse = visitorService.addVisitor(visitor);
			return createVisitorGenericResponse;
		} catch (Exception e) {
			return createVisitorGenericResponse;
		}
	}

	@ApiOperation(value = "Get User by Id")
	@GetMapping(GET_VISITOR + "/{" + ID + "}")
	public GenericResponse<VisitorDto> getVisitorById(@PathVariable(value = ID) Integer id) {
		GenericResponse<VisitorDto> getVisitorGenericResponse = null;
		try {
			getVisitorGenericResponse = visitorService.getVisitorById(id);
			return getVisitorGenericResponse;
		} catch (Exception e) {
			return getVisitorGenericResponse;
		}
	}

	@ApiOperation(value = "Get All visitors from system")
	@GetMapping(LIST_VISITOR)
	public GenericResponse<VisitorDto> listVisitors(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) String pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) String pageSize,
			@RequestParam(value = "sort", defaultValue = "firstName", required = false) String sort) {
		log.info("list visitor");
		GenericResponse<VisitorDto> listVisitorGenericResponse = null;
		try {
			listVisitorGenericResponse = visitorService.listVisitors(pageNo, pageSize, sort);
			return listVisitorGenericResponse;
		} catch (Exception e) {
			log.error("error" + e.getMessage());
			return listVisitorGenericResponse;
		}
	}

	@ApiOperation(value = "Delete Visitor from system")
	@DeleteMapping(DELETE_VISITOR + "/{id}")
	public GenericResponse<?> deleteVisitor(@PathVariable(value = "id") Integer visitorId) {
		GenericResponse<?> deleteVisitorGenericResponse = null;
		try {
			deleteVisitorGenericResponse = visitorService.deleteVisitor(visitorId);
			return deleteVisitorGenericResponse;
		} catch (Exception e) {
			log.error(e.getMessage());
			return deleteVisitorGenericResponse;
		}
	}

	@ApiOperation(value = "Update Visitor details")
	@PutMapping(UPDATE_VISITOR)
	public ResponseEntity<GenericResponse<VisitorDto>> updateVisitor(@Valid @RequestBody VisitorDto visitor) {
		try {
			GenericResponse<VisitorDto> updateVisitorGenericResponse = visitorService.updateVisitor(visitor);
			return ResponseEntity.status(updateVisitorGenericResponse.getMessageCode())
					.body(updateVisitorGenericResponse);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}