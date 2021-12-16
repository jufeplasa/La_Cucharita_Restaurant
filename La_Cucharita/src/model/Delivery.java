package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Delivery implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private List<Dish> order;
	
	private String code;
	private Calendar date;
	private State deliveryState;
	
	
	
	public Delivery(Dish newdish, String code) {
		this.code = code;
		deliveryState = State.PENDIENTE;
		order=new ArrayList<Dish>();
		setDate(GregorianCalendar.getInstance());
	}

	public void addOrder(List<Dish> newdish) {
		order=newdish;
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


	public void setDeliveryState(String deliveryState) {
		switch (deliveryState) {

		case "ENTREGADO":
			this.deliveryState = State.ENTREGADO;
			break;

		case "EN_PROCESO":
			this.deliveryState =State.EN_PROCESO;
			break;

		case "PENDIENTE":
			this.deliveryState = State.PENDIENTE;
			break;
		}
	}
	
	public void addDish(Dish dish) {
		order.add(dish);
	}
	
	public String showOrder() {
		String message="";
		
		for(int i=0;i<order.size();i++) {
			message+=order.get(i).getName()+"......  "+order.get(i).getCost();
		}
		return message;
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
	


