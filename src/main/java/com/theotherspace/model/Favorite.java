package com.theotherspace.model;

public class Favorite {
	
	private long id, userId, movieId;
	
	public Favorite() {}

	public Favorite(long id, long userId, long movieId) {
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	
	

}
