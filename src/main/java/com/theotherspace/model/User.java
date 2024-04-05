package com.theotherspace.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "username", unique = true)
	private String username;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "email", unique = true)
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	@Column(name = "card_code")
	private String cardCode;
	@Column(name = "card_points")
	private Integer cardPoints;
	
	@ManyToMany(mappedBy = "users")
	List<Movie> movies;
	
	@OneToMany(mappedBy = "user")
	List<Review> reviews;
	@OneToMany(mappedBy = "user")
	List<Ticket> tickets;
	
	public User() {}

	public User(long id, String username, String firstName, String lastName, String email, String password,
			LocalDate dateOfBirth) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public Integer getCardPoints() {
		return cardPoints != null ? cardPoints : 0;
	}

	public void setCardPoints(Integer cardPoints) {
	    if (this.cardPoints == null) {
	        this.cardPoints = cardPoints;
	    } else {
	        this.cardPoints += cardPoints;
	    }
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", dateOfBirth=" + dateOfBirth + "]";
	}

	public List<Movie> getMovies() {
	    if (movies == null) {
	        return new ArrayList();
	    } else {
	        return movies;
	    }
	}


	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	

}
