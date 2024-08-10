package com.org.ticketmanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.ticketmanagement.entity.Ticket;
import com.org.ticketmanagement.tools.TicketmanagementConstantes.TicketmanagementConstantesDAO;
 
@Repository(TicketmanagementConstantesDAO.TICKETS_DAO_KEY) 
public interface ITicketRepository extends JpaRepository<Ticket, Integer> {  

}