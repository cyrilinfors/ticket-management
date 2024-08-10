package com.org.ticketmanagement.dto;

public class TicketMediumDTO  extends TicketBasicDTO {

	private static final long serialVersionUID = 1L;
 
	private UserBasicDTO user;

	public UserBasicDTO getUser() {
		return user;
	}

	public void setUser(UserBasicDTO user) {
		this.user = user;
	}

	 

}