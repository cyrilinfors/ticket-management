package com.org.ticketmanagement.exception;

public class TicketmanagementBusinessException extends Exception {  
	  
	private static final long serialVersionUID = 1L; 

	public TicketmanagementBusinessException(String message, Throwable cause) {
	 super(message, cause);
	}
	public TicketmanagementBusinessException(String message) {
	 super(message);
	}
}