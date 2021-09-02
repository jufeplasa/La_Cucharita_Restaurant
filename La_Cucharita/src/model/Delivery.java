package model;

import java.time.LocalDate;

public class Delivery {
	
	private String code;
	private int amount;
	private LocalDate date;
	private State deliveryState;
	
	
	public Delivery(String code, int amount, LocalDate date, State deliveryState) {
		this.code = code;
		this.amount = amount;
		this.date = date;
		this.deliveryState = deliveryState;
		
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public State getDeliveryState() {
		return deliveryState;
	}


	public void setDeliveryState(State deliveryState) {
		this.deliveryState = deliveryState;
	}
	
	
		
		
		
}
	


