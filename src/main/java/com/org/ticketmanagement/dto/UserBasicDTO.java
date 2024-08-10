
package com.org.ticketmanagement.dto;

public class UserBasicDTO  implements IDTO  {

  	private static final long serialVersionUID = 1L;


	private Integer id;

	
	private String email;	

	private String username;	
  

  
	public Integer getId() {
		 return id;
	}
 
	public String getEmail() {
		return email;
	}
	
	public String getUsername() {
		return username;
	}
	  

  
	public void setId(Integer id) {
		 this.id = id;
	}

 
	public void setEmail(String email) {
		this.email = email;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	 

 }