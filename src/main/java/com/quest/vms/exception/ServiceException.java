package com.quest.vms.exception;


/** The type Service exception. */
public class ServiceException extends RuntimeException {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
   * Instantiates a new Service exception.
   *
   * @param message the message
   */
  public ServiceException(String message) {
    super(message);
  }
}
