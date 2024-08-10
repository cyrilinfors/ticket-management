package com.tech;

import java.nio.file.AccessDeniedException;
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
import com.org.ticketmanagement.dto.UserBasicDTO;
import com.org.ticketmanagement.dto.UserFullDTO;
import com.org.ticketmanagement.exception.TicketmanagementBusinessException;
import com.org.ticketmanagement.service.IUserService;

import jakarta.annotation.Resource;

@ContextConfiguration(classes = { TicketmanagementConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {
	 

	@Resource(name = "usersService")
	private IUserService usersService;

	private Integer userIdForAllTest;

	@Test
	public void testCreateTicket() throws TicketmanagementBusinessException {

		Assert.assertTrue(this.userIdForAllTest != null);
	}

	@Test
	public void testGetOneUser() throws TicketmanagementBusinessException {

		 UserFullDTO user = this.usersService.findById(this.userIdForAllTest);
		Assert.assertTrue(user != null);
	}
	
	@Test
	public void testGetAllUsers() {

		  List<UserBasicDTO> listuser = this.usersService.findAll();
		Assert.assertTrue(listuser.size() > 0);
	}

	@Test
	public void testUpdateUsers() throws TicketmanagementBusinessException, AccessDeniedException {
		UserFullDTO userDto = this.usersService.findById(this.userIdForAllTest);
		userDto.setEmail("example100@gmail.com");
		userDto = this.usersService.update(userDto);
		Assert.assertTrue(userDto.getEmail().equalsIgnoreCase("example100@gmail.com"));
	} 
	@BeforeEach
	public void prepareAllEntityBefore() throws TicketmanagementBusinessException {
		UserFullDTO userdto = new UserFullDTO();
		userdto.setEmail("example@gmail.com");
		userdto.setUsername("doe");
		UserFullDTO user = this.usersService.create(userdto);
		 
		this.userIdForAllTest = user.getId();

	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, TicketmanagementBusinessException {
		if (this.userIdForAllTest != null) {
			this.usersService.deleteById(this.userIdForAllTest);
		}
	}
}
