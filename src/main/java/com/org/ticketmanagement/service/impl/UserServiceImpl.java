package com.org.ticketmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ticketmanagement.dao.IUserRepository;
import com.org.ticketmanagement.dto.UserBasicDTO;
import com.org.ticketmanagement.dto.UserFullDTO;
import com.org.ticketmanagement.entity.User;
import com.org.ticketmanagement.service.IUserService;

@Service("usersService")
public class UserServiceImpl extends
		AbstractTicketmanagementServiceImpl<User, UserBasicDTO, UserFullDTO, IUserRepository> implements IUserService {
	@Autowired
	private IUserRepository UsersRepository;

	public UserServiceImpl() {
		super(User.class, UserBasicDTO.class, UserFullDTO.class);
	}

	@Override
	public IUserRepository getDAO() {
		return this.UsersRepository;
	}

}