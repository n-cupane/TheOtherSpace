package com.theotherspace.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "ticket")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "price")
	private double price;
	@Column(name = "seat")
	private int seat;
	
	@ManyToOne
	private User user;
	@ManyToOne
	private Screening screening;

	
	public Ticket() {}

	public Ticket(Long id, User user, Screening screening, double price, int seat) {
		super();
		this.id = id;
		this.user = user;
		this.screening = screening;
		this.price = price;
		this.seat = seat;
	}
	
	public Ticket(User user, Screening screening, double price, int seat) {
		super();
		this.user = user;
		this.screening = screening;
		this.price = price;
		this.seat = seat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Screening getScreening() {
		return screening;
	}

	public void setScreening(Screening screeningId) {
		this.screening = screening;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", userId=" + user.getId() + ", screeningId=" + screening.getId() + ", price=" + price
				+ ", seat=" + seat + "]";
	}

}
