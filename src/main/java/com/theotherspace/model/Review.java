package com.theotherspace.model;

import java.time.LocalDate;

public class Review {

	private long id, userId, movieId;
	private String text;
	private int rating;
	private LocalDate date;
	
	public Review() {}

	public Review(long id, long userId, long movieId, String text, int rating, LocalDate date) {
		super();
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.text = text;
		this.rating = rating;
		this.date = date;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
