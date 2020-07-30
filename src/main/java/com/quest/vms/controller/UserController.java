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
import com.quest.vms.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/" + USER_URL_PATH)
@Api(value = "Visitor Management System", description = "Operations pertaining to Visitor Management System")
@Slf4j
public class UserController {

	@Autowired
	IUserService userService;

	@ApiOperation(value = "Add a User to system")
	@PostMapping(CREATE_USER)
	public ResponseEntity<?> create(@Valid @RequestBody VisitorDto user) {
		VisitorDto userDto = null;
		try {
			userDto = userService.create(user);
		} catch (Exception e) {
			log.error("error while saving user");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<VisitorDto>(userDto, HttpStatus.OK);
	}

	@ApiOperation(value = "Get User by Id")
	@GetMapping(GET_USER + "/{id}")
	public ResponseEntity<?> get(@PathVariable(value = "id") long id) {
		VisitorDto userDto;
		try {
			userDto = userService.getUserById(id);
		} catch (RecordNotFoundException e) {
			log.error("error while fetching user");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(userDto);
	}

	@ApiOperation(value = "Delete User from system")
	@DeleteMapping(DELETE_USER + "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
		try {
			userService.delete(id);
		} catch (Exception e) {
			log.error("error while deleting user");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
