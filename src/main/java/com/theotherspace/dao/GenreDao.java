package com.theotherspace.dao;

import com.theotherspace.model.Genre;

public interface GenreDao extends GeneralDao<Genre> {
	
	abstract public Genre findGenreByName(String genreName);

}
