package com.theotherspace.dao;

import com.theotherspace.model.Theater;

public interface TheaterDao extends GeneralDao<Theater>{

	abstract public Theater findTheaterByNumber(int theaterNumber);
	
}
