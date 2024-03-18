package com.theotherspace.model;

public class Favorite {
	
	private long userId, movieId;
	
	public Favorite() {}

	public Favorite(long userId, long movieId) {
		super();
		this.userId = userId;
		this.movieId = movieId;
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
