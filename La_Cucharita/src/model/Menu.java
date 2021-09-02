package model;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	
	private List<Dish> dishes;
	
	
	public Menu() {
		dishes = new ArrayList<Dish>();
			
	}


	public List<Dish> getDishes() {
		return dishes;
	}


	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	
	

}
