
package com.org.ticketmanagement.dto; 
import java.sql.Timestamp;
 
public class TicketBasicDTO  implements IDTO  {

  	private static final long serialVersionUID = 1L;


	private Integer id;

	
	private Timestamp updatedAt;	

	private String description;	

	private Timestamp createdAt;	

	private String title;	

	 

	private String status;	
 

  
	 

	public TicketBasicDTO(Integer id, String description,   String title,
			String status) {
		super();
		this.id = id; 
		this.description = description; 
		this.title = title;
		this.status = status;
	}

	 

	public TicketBasicDTO() {
	}



	public Integer getId() {
		 return id;
	}
 
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public String getTitle() {
		return title;
	}
	
 
	
	public String getStatus() {
		return status;
	}
 

  
	public void setId(Integer id) {
		 this.id = id;
	}

 
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	
	 

	
	public void setStatus(String status) {
		this.status = status;
	}

 

 }