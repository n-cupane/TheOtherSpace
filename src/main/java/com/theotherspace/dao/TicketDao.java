package com.theotherspace.dao;

import java.util.List;

import com.theotherspace.model.Ticket;

public interface TicketDao extends GeneralDao<Ticket> {

	abstract public List<Ticket> findAllTicketsOfUser(long userId);
	
	abstract public List<Ticket> findAllTicketsForScreening(long screeningId);
	
	abstract public List<Ticket> findAllTicketsForMovie(long movieId);
	
	abstract public List<Ticket> findAllTicketsOfUserOrderByDate(long userId);
	
}
