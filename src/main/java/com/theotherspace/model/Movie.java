package com.theotherspace.model;

import com.theotherspace.utilities.BusinessLogic;

public class Movie {
	
	private long id, genreId;
	private String title, description, imageUrl;
	private int duration;
	private boolean over18;
	
	public Movie() {}

	public Movie(long id, long genreId, String title, String description, int duration, boolean over18) {
		super();
		this.id = id;
		this.genreId = genreId;
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

	public long getGenreId() {
		return genreId;
	}

	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}
	
	public void setGenreFromName(String genreName) {
		
		if (BusinessLogic.findGenreByName(genreName) != null) {
			 this.genreId = BusinessLogic.findGenreByName(genreName).getId();
		} else {
			Genre genreToAdd = new Genre();
			genreToAdd.setName(genreName);
			BusinessLogic.addGenre(genreToAdd);
			this.genreId = BusinessLogic.findGenreByName(genreName).getId();
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
