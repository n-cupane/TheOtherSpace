package com.theotherspace.model;

import java.time.LocalDateTime;

public class Screening {
	
	private long id, theaterId, movieId;
	private LocalDateTime dateTime;
	
	public Screening() {}

	public Screening(long id, long theaterId, long movieId, LocalDateTime dateTime) {
		super();
		this.id = id;
		this.theaterId = theaterId;
		this.movieId = movieId;
		this.dateTime = dateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(long theaterId) {
		this.theaterId = theaterId;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	

}
