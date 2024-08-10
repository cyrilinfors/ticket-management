package com.org.ticketmanagement.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ticketmanagement.dto.TicketBasicDTO;
import com.org.ticketmanagement.dto.TicketFullDTO;
import com.org.ticketmanagement.exception.TicketmanagementBusinessException;
import com.org.ticketmanagement.service.ITicketService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tickets")

public class TicketController {

	@Resource(name = "ticketsService")
	private ITicketService ticketsService;

	/**
	 * Find all Tickets in the database
	 **/
	@GetMapping
	@Operation(summary = "Retrieve all tickets in the database")
	public ResponseEntity<List<TicketBasicDTO>> getAllTickets() {

		return ResponseEntity.ok(ticketsService.findAll());

	}

	/**
	 * Create a new Tickets in the database
	 **/
	@PostMapping
	@Operation(summary = "Create a new ticket in the database")
	public ResponseEntity<?> create(@RequestBody TicketFullDTO ticketsFullDTO)
			throws TicketmanagementBusinessException {
		ticketsFullDTO = ticketsService.create(ticketsFullDTO);
		return ResponseEntity.ok().body(ticketsFullDTO);
	}

	/**
	 * Update a given Tickets in the database
	 **/
	@PutMapping("/{id}")
	@Operation(summary = "Update a ticket by ID in the database")
	public ResponseEntity<?> update(@PathVariable(value = "id") int id, @RequestBody TicketFullDTO ticketsFullDTO)
			throws TicketmanagementBusinessException, AccessDeniedException, java.nio.file.AccessDeniedException {
		ticketsFullDTO = ticketsService.updateTickets(ticketsFullDTO);
		return ResponseEntity.ok().body(ticketsFullDTO);
	}

	/**
	 * Delete a given Tickets in the database
	 **/
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a ticket by ID from the database")
	public ResponseEntity<TicketBasicDTO> deleteTickets(@PathVariable(value = "id") int idtickets)
			throws AccessDeniedException, TicketmanagementBusinessException {
		ticketsService.deleteById(idtickets);
		return ResponseEntity.ok().build();

	}

	/**
	 * Find a given Tickets in the database
	 **/

	@GetMapping("/{id}")
	@Operation(summary = "Retrieve a ticket by ID from the database")
	public ResponseEntity<?> findOne(@PathVariable(value = "id") int id) throws TicketmanagementBusinessException {
		TicketFullDTO fullDTO = ticketsService.findById(id);
		return ResponseEntity.ok().body(fullDTO);
	}

	@PutMapping("/{id}/assign/{userId}")
	@Operation(summary = "Assign a ticket to a user")
	public TicketFullDTO assignTicketToUser(@PathVariable int id, @PathVariable int userId) {
		return ticketsService.assignTicketToUser(id, userId);
	}

}
