package com.quest.vms.controller;

import static com.quest.vms.common.utils.VmsConstants.ID;
import static com.quest.vms.common.utils.VmsConstants.VISITOR;
import static com.quest.vms.common.utils.VmsConstants.VISITOR_URL_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import com.quest.vms.dto.OtpDTO;
import com.quest.vms.dto.ValidateOtpDTO;
import com.quest.vms.dto.VisitorDTO;
import com.quest.vms.dto.VisitorsCountDTO;
import com.quest.vms.service.VisitorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/" + VISITOR_URL_PATH)
@Api(value = "Visitor Management System", description = "Operations pertaining to Visitor Management System")
@Slf4j
public class VisitorController {

	private static final String VISITORCOUNT = "/visitorcount";
	private static final String VISITOR_OTP = "/visitor-otp";
	private static final String OTPNUMBER = "otpNumber";
	private static final String VALIDATE_OTP = "/validate-otp";
	@Autowired
	private VisitorService visitorService;

	@ApiOperation(value = "Add a Visitor to system")
	@PostMapping(VISITOR)
	public GenericResponse<VisitorDTO> addVisitor(@RequestBody VisitorDTO visitor) {
		GenericResponse<VisitorDTO> createVisitorGenericResponse = null;
		try {
			createVisitorGenericResponse = visitorService.addVisitor(visitor);
			return createVisitorGenericResponse;
		} catch (Exception e) {
			return createVisitorGenericResponse;
		}
	}

	@ApiOperation(value = "Get User by Id")
	@GetMapping(VISITOR + "/{" + ID + "}")
	public GenericResponse<VisitorDTO> getVisitorById(@PathVariable(value = ID) Integer id) {
		GenericResponse<VisitorDTO> getVisitorGenericResponse = null;
		try {
			getVisitorGenericResponse = visitorService.getVisitorById(id);
			return getVisitorGenericResponse;
		} catch (Exception e) {
			return getVisitorGenericResponse;
		}
	}

	@ApiOperation(value = "Get All visitors from system")
	@GetMapping(VISITOR)
	public GenericResponse<VisitorDTO> listVisitors(
			@RequestParam(value = "index", defaultValue = "0", required = false) String index,
			@RequestParam(value = "size", defaultValue = "10", required = false) String size,
			@RequestParam(value = "sortBy", defaultValue = "firstName", required = false) String sortBy,
			@RequestParam(value = "orderBy", defaultValue = "ASC", required = false) Sort.Direction orderBy) {
		log.info("list visitor");
		GenericResponse<VisitorDTO> listVisitorGenericResponse = null;
		try {
			listVisitorGenericResponse = visitorService.listVisitors(index, size, sortBy, orderBy);
			return listVisitorGenericResponse;
		} catch (Exception e) {
			log.error("error" + e.getMessage());
			return listVisitorGenericResponse;
		}
	}

	@ApiOperation(value = "Get visitors count to display on dashboard")
	@GetMapping(VISITORCOUNT)
	public GenericResponse<VisitorsCountDTO> getVisitorsCount() {
		log.info("list visitor count");
		GenericResponse<VisitorsCountDTO> visitorCountGenericResponse = null;
		try {
			visitorCountGenericResponse = visitorService.listVisitorsCount();
			return visitorCountGenericResponse;
		} catch (Exception e) {
			log.error("error" + e.getMessage());
			return visitorCountGenericResponse;
		}
	}

	@ApiOperation(value = "Delete Visitor from system")
	@DeleteMapping(VISITOR + "/{id}")
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
	@PutMapping(VISITOR)
	public GenericResponse<VisitorDTO> updateVisitor(@RequestBody VisitorDTO visitor) {
		GenericResponse<VisitorDTO> updateVisitorGenericResponse = null;
		try {
			updateVisitorGenericResponse = visitorService.updateVisitor(visitor);
			return updateVisitorGenericResponse;
		} catch (Exception e) {
			log.error(e.getMessage());
			return updateVisitorGenericResponse;
		}
	}

	@ApiOperation(value = "Get All visitors by filter")
	@GetMapping("/listVisitor")
	public GenericResponse<VisitorDTO> searchVisitor(
			// approved or not
			@RequestParam(value = "visitorType", required = false) String visitorType,
			// if not specified, default is today's date
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			// visitor name
			@RequestParam(value = "visitorName", required = false) String visitorName,
			// get all visitor by contact person name
			@RequestParam(value = "contactPersonName", required = false) String contactPersonName,
			@RequestParam(value = "isActive", required = false) String isActive) {
		GenericResponse<VisitorDTO> filerListVisitorGenericResponse = null;
		try {
			filerListVisitorGenericResponse = visitorService.searchVisitor(visitorType, startDate, endDate,
					visitorName, contactPersonName, isActive);
			return filerListVisitorGenericResponse;
		} catch (Exception e) {
			log.error(e.getMessage());
			return filerListVisitorGenericResponse;
		}
	}
	
	@ApiOperation(value = "Call Email Service to Generate OTP")
	@PostMapping(VISITOR_OTP)
	public GenericResponse<OtpDTO> generateOTP(@RequestBody OtpDTO otpDto) {
		GenericResponse<OtpDTO> generateOtpGenericResponse = null;
		try {
			generateOtpGenericResponse = visitorService.generateOtp(otpDto);
			return generateOtpGenericResponse;
		} catch (Exception e) {
			return generateOtpGenericResponse;
		}
	}	
	
	
	@ApiOperation(value = "Validate OTP")
	@PostMapping(VALIDATE_OTP)
	public GenericResponse<Boolean> validateOtp(@RequestBody ValidateOtpDTO validateOtpDTO) {
		GenericResponse<Boolean> validateOtpGenericResponse = null;
		try {
			validateOtpGenericResponse = visitorService.validateOtp(validateOtpDTO);
			return validateOtpGenericResponse;
		} catch (Exception e) {
			return validateOtpGenericResponse;
		}
	}



}