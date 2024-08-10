package com.tech;

import java.nio.file.AccessDeniedException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import com.org.ticketmanagement.config.TicketmanagementConfig;
import com.org.ticketmanagement.dto.TicketBasicDTO;
import com.org.ticketmanagement.dto.TicketFullDTO;
import com.org.ticketmanagement.dto.UserFullDTO;
import com.org.ticketmanagement.exception.TicketmanagementBusinessException;
import com.org.ticketmanagement.service.ITicketService;
import com.org.ticketmanagement.service.IUserService;

import jakarta.annotation.Resource;

@ContextConfiguration(classes = { TicketmanagementConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TicketServiceTest {

	@Resource(name = "ticketsService")
	private ITicketService ticketsService;

	@Resource(name = "usersService")
	private IUserService usersService;

	private Integer ticketIdForAllTest;

	@Test
	public void testCreateTicket() throws TicketmanagementBusinessException {

		Assert.assertTrue(this.ticketIdForAllTest != null);
	}

	@Test
	public void testGetAllTickets() {

		List<TicketBasicDTO> listTicket = this.ticketsService.findAll();
		Assert.assertTrue(listTicket.size() > 0);
	}

	@Test
	public void testUpdateTickets() throws TicketmanagementBusinessException {
		TicketFullDTO ticketDto = this.ticketsService.findById(this.ticketIdForAllTest);
		ticketDto.setTitle("title1000");
		ticketDto = this.ticketsService.updateTickets(ticketDto);
		Assert.assertTrue(ticketDto.getTitle().equalsIgnoreCase("title1000"));
	}


	@Test
	public void testGetOneTicket() throws TicketmanagementBusinessException {

		  TicketFullDTO ticketDto = this.ticketsService.findById(this.ticketIdForAllTest);
		Assert.assertTrue(ticketDto != null);
	}
	
	@Test
	public void testAssignTicketToUser() throws TicketmanagementBusinessException {
		UserFullDTO userdto = new UserFullDTO();
		userdto.setEmail("example3@gmail.com");
		userdto.setUsername("doe3");
		UserFullDTO user3 = this.usersService.create(userdto);
		  TicketFullDTO ticket = this.ticketsService.assignTicketToUser(this.ticketIdForAllTest, user3.getId());
		  System.out.println("eeeeee"+ticket.getUser().getId());
		  Integer iduser = ticket.getUser().getId();
		  Assert.assertTrue(iduser == user3.getId());
	}
	@BeforeEach
	public void prepareAllEntityBefore() throws TicketmanagementBusinessException {
		UserFullDTO userdto = new UserFullDTO();
		userdto.setEmail("example@gmail.com");
		userdto.setUsername("doe");
		UserFullDTO user = this.usersService.create(userdto);
		TicketFullDTO ticketFullDTO = new TicketFullDTO();
		ticketFullDTO.setDescription("Sample Description");
		ticketFullDTO.setStatus("en_cours");
		ticketFullDTO.setTitle("Sample Ticket");
		ticketFullDTO.setUser(user);
		ticketFullDTO.setCreatedAt(Timestamp.from(Instant.now()));
		ticketFullDTO.setUpdatedAt(Timestamp.from(Instant.now()));
		TicketFullDTO createdTicket = this.ticketsService.create(ticketFullDTO);
		this.ticketIdForAllTest = createdTicket.getId();

	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, TicketmanagementBusinessException {
		if (this.ticketIdForAllTest != null) {
			this.ticketsService.deleteById(this.ticketIdForAllTest);
		}
	}
}