package com.quest.vms.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** The type Error codes. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodes {
	
	  /** The constant SUCCESS_STATUS_CODE. */
	  public static final int SUCCESS_STATUS_CODE = 200;

	  /** The constant SIGN_IN_ERROR_STATUS_CODE. */
	  public static final int SIGN_UP_ERROR_STATUS_CODE = 404;
	  
	  /** The constant BAD_REQUEST_STATUS_CODE. */
	  public static final int BAD_REQUEST_STATUS_CODE = 400;

	  /** The constant UNAUTHORIZED_REQUEST_STATUS_CODE. */
	  public static final int UNAUTHORIZED_REQUEST_STATUS_CODE = 401;
}
