package com.quest.vms.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.bind.annotation.RestController;
import com.quest.vms.customexception.InternalServerError;
import com.quest.vms.customexception.RecordNotFoundException;
import com.quest.vms.dto.VisitorDto;
import com.quest.vms.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value = "Visitor Management System", description = "Operations pertaining to Visitor Management System")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	@ApiOperation(value = "Add a User to system")
	@PostMapping("/create")
	public ResponseEntity<?> create(@Valid @RequestBody VisitorDto user) {
		VisitorDto userDto = null;

		try {
			
			System.out.println("enterd in controller");
			userDto = userService.create(user);
		} catch (Exception e) {
			logger.error("error while saving record in DB :" + e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<VisitorDto>(userDto, HttpStatus.OK);
	}

	@ApiOperation(value = "Get User by Id")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable(value = "id") long id) {
		VisitorDto userDto;
		try {
			userDto = userService.getUserById(id);
		} catch (RecordNotFoundException e) {
			logger.error("error while fetching record from DB :" + e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok().body(userDto);
	}

	
	@ApiOperation(value = "Delete User from system")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") int id){

		try {
			 userService.delete(id);
		}catch(Exception e) {
			logger.error("error while fetching record from DB to update:" + e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
			return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
