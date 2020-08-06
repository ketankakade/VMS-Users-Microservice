package com.quest.vms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends Exception {

	private static final long serialVersionUID = -2422618611403003611L;

	public InternalServerError(String exception) {
		super(exception);
	}
}
