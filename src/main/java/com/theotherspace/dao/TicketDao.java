package com.theotherspace.dao;

import java.util.List;

import com.theotherspace.model.Ticket;

public interface TicketDao extends GeneralDao<Ticket> {

	abstract public List<Ticket> findAllTicketsOfUser(long userId);
	
}
