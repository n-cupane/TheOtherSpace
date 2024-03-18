package com.theotherspace.model;

public class Theater {
	
	private long id;
	private int seats, number;
	
	public Theater() {}

	public Theater(long id, int seats, int number) {
		super();
		this.id = id;
		this.seats = seats;
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

}
