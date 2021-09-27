package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Delivery {
	
	private List<Dish> order;
	
	private String code;
	private Calendar date;
	private State deliveryState;
	
	
	
	public Delivery(Dish newdish, String code) {
		this.code = code;
		deliveryState = State.PENDIENTE;
		order=new ArrayList<Dish>();
		addOrder(newdish);
		setDate(GregorianCalendar.getInstance());
	}

	public void addOrder(Dish newdish) {
		order.add(newdish);
	}
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public State getDeliveryState() {
		return deliveryState;
	}


	public void setDeliveryState(State deliveryState) {
		this.deliveryState = deliveryState;
	}


	public List<Dish> getOrder() {
		return order;
	}


	public void setOrder(List<Dish> order) {
		this.order = order;
	}


	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
	}

}
	


