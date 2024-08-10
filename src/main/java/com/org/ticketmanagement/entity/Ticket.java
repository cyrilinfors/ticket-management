
package com.org.ticketmanagement.entity; 
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "tickets")
public class Ticket  implements IEntity  {


	private static final long serialVersionUID = 1L;



	public Ticket() { 
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Integer id;


	@Column(name = "updated_at")
	private Timestamp updatedAt;


	@Column(name = "description")
	private String description;


	@Column(name = "created_at")
	private Timestamp createdAt;


	@Column(name = "title")
	private String title;



	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;


	@Column(name = "status")
	private String status;
	 
	 

	public Ticket(Integer id, Timestamp updatedAt, String description, Timestamp createdAt, String title, User user,
			String status) {
		super();
		this.id = id;
		this.updatedAt = updatedAt;
		this.description = description;
		this.createdAt = createdAt;
		this.title = title;
		this.user = user;
		this.status = status;
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


	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setStatus(String status) {
		this.status = status;
	}




}