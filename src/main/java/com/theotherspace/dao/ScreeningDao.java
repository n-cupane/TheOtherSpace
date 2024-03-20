package com.theotherspace.dao;


import java.util.List;

import com.theotherspace.model.Screening;

public interface ScreeningDao extends GeneralDao<Screening>{
	
	abstract public List<Screening> findAllScreeningsByMovieId(long movieId);

}
