package com.theotherspace.model;

public class Ticket {
	
	private long id, userId, screeningId;
	private double price;
	private int seat;
	
	public Ticket() {}

	public Ticket(long id, long userId, long screeningId, double price, int seat) {
		super();
		this.id = id;
		this.userId = userId;
		this.screeningId = screeningId;
		this.price = price;
		this.seat = seat;
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

	public long getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(long screeningId) {
		this.screeningId = screeningId;
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
		return "Ticket [id=" + id + ", userId=" + userId + ", screeningId=" + screeningId + ", price=" + price
				+ ", seat=" + seat + "]";
	}

}
