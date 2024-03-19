package com.theotherspace.dao;

import java.util.List;

import com.theotherspace.model.Genre;
import com.theotherspace.model.Movie;

public interface MovieDao extends GeneralDao<Movie> {
	
	abstract public Movie findMovieByTitle(String title);
	
	abstract public List<Movie> findAllMoviesForGenre(Genre g);

}
