package com.quest.vms.controller;

import static com.quest.vms.common.utils.VmsConstants.CREATE_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.DELETE_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.GET_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.ID;
import static com.quest.vms.common.utils.VmsConstants.LIST_VISITOR;
import static com.quest.vms.common.utils.VmsConstants.VISITOR_URL_PATH;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest.vms.common.utils.GenericResponse;
import com.quest.vms.dto.VisitorDto;
import com.quest.vms.service.IVisitorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/" + VISITOR_URL_PATH)
@Api(value = "Visitor Management System", description = "Operations pertaining to Visitor Management System")
@Slf4j
public class VisitorController {

	@Autowired
	private IVisitorService visitorService;

	@ApiOperation(value = "Add a Visitor to system")
	@PostMapping(CREATE_VISITOR)
	public ResponseEntity<GenericResponse<VisitorDto>> addVisitor(@Valid @RequestBody VisitorDto visitor) {
		try {
			GenericResponse<VisitorDto> createVisitorGenericRes = visitorService.addVisitor(visitor);
			return ResponseEntity.status(createVisitorGenericRes.getMessageCode()).body(createVisitorGenericRes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation(value = "Get User by Id")
	@GetMapping(GET_VISITOR + "/{" + ID + "}")
	public ResponseEntity<GenericResponse<VisitorDto>> getVisitorById(@PathVariable(value = ID) Integer id) {
		try {
			GenericResponse<VisitorDto> getVisitorGenericRes = visitorService.getVisitorById(id);
			return ResponseEntity.status(getVisitorGenericRes.getMessageCode()).body(getVisitorGenericRes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation(value = "Get All visistors from system")
	@GetMapping(LIST_VISITOR + "/{pageNo}/{pageSize}")
	public ResponseEntity<GenericResponse<VisitorDto>> listVisitors(@PathVariable Integer pageNo,
			@PathVariable Integer pageSize) {
		log.info("list visitor");
		try {
			GenericResponse<VisitorDto> listVisitorGenericRes = visitorService.listVisitors(pageNo, pageSize);
			return ResponseEntity.status(listVisitorGenericRes.getMessageCode()).body(listVisitorGenericRes);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation(value = "Delete Visitor from system")
	@DeleteMapping(DELETE_VISITOR + "/{id}")
	public ResponseEntity<GenericResponse<?>> deleteVisitor(@PathVariable(value = "id") Integer visitorId) {
		try {
			GenericResponse<?> deleteVisitorGenericRes = visitorService.deleteVisitor(visitorId);
			return ResponseEntity.status(deleteVisitorGenericRes.getMessageCode()).body(deleteVisitorGenericRes);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
}
