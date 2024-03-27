package com.theotherspace.model;

import java.util.List;

import com.theotherspace.dao.GenreDao;
import com.theotherspace.utilities.BusinessLogic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "movie")
@Table( )
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "title")
	private String title;
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	@Column(name = "img_url")
	private String imageUrl;
	@Column(name = "duration")
	private int duration;
	@Column(name = "for_adults")
	private boolean over18;
	
	@ManyToMany()
	@JoinTable(name = "user_movie",
				joinColumns = @JoinColumn(name = "movie_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"),
				uniqueConstraints = {@UniqueConstraint(columnNames = {"movie_id","user_id"})})
	private List<User> users;
	
	@OneToMany(mappedBy = "movie")
	List<Review> reviews;
	@OneToMany(mappedBy = "movie")
	List<Screening> screenings;
	
	@ManyToOne
	private Genre genre;
	
	public Movie() {}

	public Movie(long id, Genre genre, String title, String description, int duration, boolean over18) {
		super();
		this.id = id;
		this.genre = genre;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.over18 = over18;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public void setGenreFromName(String genreName) {
		if (BusinessLogic.findGenreByName(genreName) != null) {
			 this.genre = BusinessLogic.findGenreByName(genreName);
		} else {
			Genre genreToAdd = new Genre();
			genreToAdd.setName(genreName);
			BusinessLogic.addGenre(genreToAdd);
			this.genre = BusinessLogic.findGenreByName(genreName);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isOver18() {
		return over18;
	}

	public void setOver18(boolean over18) {
		this.over18 = over18;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	
	
}
