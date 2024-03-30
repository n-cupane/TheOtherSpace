package com.theotherspace.dao;


import java.time.LocalDateTime;
import java.util.List;

import com.theotherspace.model.Screening;

public interface ScreeningDao extends GeneralDao<Screening>{
	
	abstract public List<Screening> findAllScreeningsByMovieId(long movieId);
	
	abstract public List<Screening> findAllScreeningsByTheater(long theaterId);
	
	abstract public boolean timeSlotIsFree(long theaterId, long movieId, LocalDateTime dateTime);

}
