package com.org.ticketmanagement.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ticketmanagement.dao.ITicketRepository;
import com.org.ticketmanagement.dao.IUserRepository;
import com.org.ticketmanagement.dto.TicketBasicDTO;
import com.org.ticketmanagement.dto.TicketFullDTO;
import com.org.ticketmanagement.entity.Ticket;
import com.org.ticketmanagement.entity.User;
import com.org.ticketmanagement.exception.TicketmanagementBusinessException;
import com.org.ticketmanagement.service.ITicketService;

@Service("ticketsService")
public class TicketServiceImpl
		extends AbstractTicketmanagementServiceImpl<Ticket, TicketBasicDTO, TicketFullDTO, ITicketRepository>
		implements ITicketService {
	@Autowired
	private ITicketRepository ticketsRepository;
	@Autowired
	private IUserRepository usesRepository;

	public TicketServiceImpl() {
		super(Ticket.class, TicketBasicDTO.class, TicketFullDTO.class);
	}

	@Override
	public ITicketRepository getDAO() {
		return this.ticketsRepository;
	}

	@Override
	public TicketFullDTO updateTickets(TicketFullDTO ticketsFullDTO) throws TicketmanagementBusinessException {

		Ticket entity = this.ticketsRepository.findById(ticketsFullDTO.getId()).orElse(null);
		if (entity != null) {
			BeanUtils.copyProperties(ticketsFullDTO, entity);
			if (ticketsFullDTO.getUser() != null) {
				User u = usesRepository.findById(ticketsFullDTO.getUser().getId()).orElse(null);
				entity.setUser(u);
			}

			this.ticketsRepository.saveAndFlush(entity);
		} else {
			throw new TicketmanagementBusinessException("Tickets does not found ");
		}
		return ticketsFullDTO;
	}

	@Override
	public TicketFullDTO assignTicketToUser(int id, int userId) {
		TicketFullDTO ticketDto = new TicketFullDTO();
		Ticket ticket = this.ticketsRepository.findById(id).orElse(null);
		User user = usesRepository.findById(userId).orElse(null);
		if (ticket != null && user != null) {
			ticket.setUser(user);
			ticket = this.ticketsRepository.saveAndFlush(ticket);
			ticketDto = this.getModelMapper().map(ticket, TicketFullDTO.class);

		}
		return ticketDto;
	}

}