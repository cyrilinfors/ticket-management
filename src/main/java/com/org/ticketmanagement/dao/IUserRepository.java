package com.org.ticketmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.ticketmanagement.entity.User;
import com.org.ticketmanagement.tools.TicketmanagementConstantes.TicketmanagementConstantesDAO;
 
@Repository(TicketmanagementConstantesDAO.USERS_DAO_KEY) 
public interface IUserRepository extends JpaRepository<User, Integer> {  

}