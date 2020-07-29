package com.quest.vms.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DBException extends Exception {

	public DBException(String exception) {
		super(exception);
	}

	private static final long serialVersionUID = -5353447491059399446L;

}