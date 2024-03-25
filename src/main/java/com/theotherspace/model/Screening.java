package com.theotherspace.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "showing")
public class Screening {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@OneToMany(mappedBy = "screening")
	List<Ticket> tickets;
	
	@ManyToOne
	private Theater theater;
	@ManyToOne
	private Movie movie;
	
	public Screening() {}

	public Screening(long id, Theater theater, Movie movie, LocalDateTime dateTime) {
		super();
		this.id = id;
		this.theater = theater;
		this.movie = movie;
		this.dateTime = dateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovieId(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	@Override
	public String toString() {
		return dateTime.format(DateTimeFormatter
				.ofPattern("dd.MM HH:mm"));
	}

}
