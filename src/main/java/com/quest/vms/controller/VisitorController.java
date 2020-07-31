package com.quest.vms.controller;

import static com.quest.vms.common.utils.VmsConstants.*;

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

import com.quest.vms.customexception.RecordNotFoundException;
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
	IVisitorService visitorService;

	@ApiOperation(value = "Add a Visitor to system")
	@PostMapping(CREATE_VISITOR)
	public ResponseEntity<?> create(@Valid @RequestBody VisitorDto visitor) {
		VisitorDto userDto = null;
		try {
			userDto = visitorService.create(visitor);
		} catch (Exception e) {
			log.error("error while saving Visitor");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<VisitorDto>(userDto, HttpStatus.OK);
	}

	@ApiOperation(value = "Get Visitor by Id")
	@GetMapping(GET_VISITOR + "/{id}")
	public ResponseEntity<?> get(@PathVariable(value = "id") long id) {
		VisitorDto visitorDto;
		try {
			visitorDto = visitorService.getvisitorById(id);
		} catch (RecordNotFoundException e) {
			log.error("error while fetching visitor");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(visitorDto);
	}

	@ApiOperation(value = "Delete Visitor from system")
	@DeleteMapping(DELETE_VISITOR + "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
		try {
			visitorService.delete(id);
		} catch (Exception e) {
			log.error("error while deleting visitor");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
