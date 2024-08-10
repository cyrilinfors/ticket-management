package com.org.ticketmanagement.service;
 
import com.org.ticketmanagement.dao.IUserRepository;
import com.org.ticketmanagement.dto.UserBasicDTO;
import com.org.ticketmanagement.dto.UserFullDTO;
import com.org.ticketmanagement.entity.User;
  
public interface IUserService extends IAbstractTicketmanagementService<User, UserBasicDTO, UserFullDTO, IUserRepository> { 

	}