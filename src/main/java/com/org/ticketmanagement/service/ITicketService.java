package com.org.ticketmanagement.service;
 
import com.org.ticketmanagement.dao.ITicketRepository;
import com.org.ticketmanagement.dto.TicketBasicDTO;
import com.org.ticketmanagement.dto.TicketFullDTO;
import com.org.ticketmanagement.entity.Ticket;
import com.org.ticketmanagement.exception.TicketmanagementBusinessException;
  
public interface ITicketService extends IAbstractTicketmanagementService<Ticket, TicketBasicDTO, TicketFullDTO, ITicketRepository> 
{

	TicketFullDTO updateTickets(TicketFullDTO ticketsFullDTO) throws TicketmanagementBusinessException;

	TicketFullDTO assignTicketToUser(int id, int userId); 

	}