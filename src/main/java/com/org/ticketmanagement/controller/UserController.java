package com.org.ticketmanagement.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ticketmanagement.dto.UserBasicDTO;
import com.org.ticketmanagement.dto.UserFullDTO;
import com.org.ticketmanagement.exception.TicketmanagementBusinessException;
import com.org.ticketmanagement.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
 
@RestController
@RequestMapping("/users")
public class UserController {

	@Resource(name = "usersService")
	private IUserService usersService;

	/**
	 * Find all Users in the database
	 **/
	@GetMapping
	@Operation(summary = "Retrieve all users in the database")
	public List<UserBasicDTO> getAllUsers() {
		return usersService.findAll();
	}

	/**
	 * Create a new Users in the database
	 **/
	@PostMapping
	@Operation(summary = "Create a new user in the database")
	public ResponseEntity<?> create(@RequestBody UserFullDTO usersFullDTO) throws TicketmanagementBusinessException {
		usersFullDTO = usersService.create(usersFullDTO);
		return ResponseEntity.ok().body(usersFullDTO);
	}

	/**
	 * Update a given Users in the database
	 **/
	@PutMapping("/{id}")
	@Operation(summary = "Update a user by ID in the database")
	public ResponseEntity<?> update(@PathVariable(value = "id") int id, @RequestBody UserFullDTO usersFullDTO)
			throws TicketmanagementBusinessException, AccessDeniedException, java.nio.file.AccessDeniedException {
		usersFullDTO = usersService.update(usersFullDTO);
		return ResponseEntity.ok().body(usersFullDTO);
	}

	/**
	 * Delete a given Users in the database
	 **/
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a user by ID from the database")
	public ResponseEntity<UserBasicDTO> deleteUsers(@PathVariable(value = "id") int idusers)
			throws AccessDeniedException, TicketmanagementBusinessException {
		usersService.deleteById(idusers);
		return ResponseEntity.ok().build();

	}

	/**
	 * Find a given Users in the database
	 **/

	@GetMapping("/{id}")
	@Operation(summary = "Find a given Users in the database")
	public ResponseEntity<?> findOne(@PathVariable(value = "id") int id) throws TicketmanagementBusinessException {
		UserFullDTO fullDTO = usersService.findById(id);
		return ResponseEntity.ok().body(fullDTO);
	}

}
