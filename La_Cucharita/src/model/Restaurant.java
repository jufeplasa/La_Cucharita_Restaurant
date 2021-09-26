package model;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Restaurant {
	
	private List<Employee> worker;
	private List <Ingredient> ingredients;
	private List<Dish> menu;
	private List<Delivery> deliveries;
	
	
	
	public Restaurant() {
		setWorker(new ArrayList<Employee>());
		ingredients= new ArrayList<Ingredient>();
		menu = new ArrayList<Dish>();
		deliveries = new ArrayList<Delivery>();
		
		ingredients.add(new Ingredient("Lentejas",800,"g"));
		worker.add(new Employee("juan","contraseņa","123456",LocalDate.of(2002, 03, 23)));
	}

	
	public boolean verification(String id, String password) {
		boolean condition=false;
		for(int i=0;i<worker.size()&!condition;i++) {
			if(id.equalsIgnoreCase(worker.get(i).getId())&&password.equals(worker.get(i).getPassword())) {
				condition=true;
								
			}
		}
		return condition;
	}
	
	public boolean changePassword(String idR, String password) {
		boolean done = false;
		for(int i=0; i<worker.size()&!done; i++) {
			if(idR.equalsIgnoreCase(worker.get(i).getId())) {
				worker.get(i).setPassword(password);
				done = true;
			}
		}
		return done;
	}
	
	public boolean addWorker(Employee newEmployee) {
		for(int i=0;i<worker.size();i++) {
			if(newEmployee.getId().equalsIgnoreCase(worker.get(i).getId())) {
				return false;
			}
		}
		if(worker.add(newEmployee)) {
			return true;
		}
		return false;
	}

	
	public int addIngrendient(Ingredient newIngredient) {
		int action=0;
		boolean conti=true;
		for(int i=0;i<ingredients.size()&&conti;i++) {
			if(ingredients.get(i).getName().equalsIgnoreCase(newIngredient.getName())) {
				ingredients.get(i).editAmount(newIngredient.getAmount());
				ingredients.get(i).setMeasure(newIngredient.getMeasure());
				conti=false;
				return 1;
			}
		}
		if (ingredients.add(newIngredient)&&conti) {
			return 2;
		}
		
		return action;
	}
	
	public void deleteIngredient(String nameIngredient) {
		for(int i=0;i<ingredients.size();i++) {
			if(ingredients.get(i).getName().equalsIgnoreCase(nameIngredient)) {
				ingredients.remove(i);
			}
		}
	}
	
	public boolean createCombo(String name,double price ,boolean isAdded) {
		for(int i=0; i<menu.size() && isAdded==false; i++) {
			if(name.equals(menu.get(i).getName())){
				isAdded = true;				
			}					
		}
		if(isAdded==false) {
			menu.add(new Dish(name, price));
			
		}
		return isAdded;
		
	}
	
	public boolean addRecipe(String name, String ingredient, double quantity, String measure ) {
		boolean found = true;
		int position = 0;
		
		for(int i=0; i<menu.size() && found; i++) {
			if(name.equals(menu.get(i).getName())){
				found = false;
				position = i;								
			}					
		}
		
		for(int i=0; i<menu.get(position).getRecipe().size();i++) {
			if(menu.get(position).getRecipe().get(i).getName().equals(ingredient)) {
				return false;
			}
		}
		return menu.get(position).addRecipe(ingredient, quantity, measure);
	}
	
	public String addOrderToDelivery(String nameDish, String code) {
		boolean found = false;
		String message="";
		Dish newdish = null;
				
		message=findOrders(code);
		return message;
	}
	
	public String findOrders(String code) {
		String message="";
		boolean found=false;
		int position=0;
		for(int i=0; i<deliveries.size() && !found; i++) {
			if(code.equals(deliveries.get(i).getCode())){
				found = true;
				position = i;								
			}					
		}
		
		for(int i=0; i<deliveries.get(position).getOrder().size();i++) {
			message+=deliveries.get(position).getOrder().get(i).getName()+"_ _ _ _ _ _ _ _ _ "+deliveries.get(position).getOrder().get(i).getCost()+"\n";

			System.out.println(message);
		}
		return message;
	}

	public List<Employee> getWorker() {
		return worker;
	}

	public void setWorker(List<Employee> worker) {
		this.worker = worker;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}


	public List<Dish> getMenu() {
		return menu;
	}


	public List<Delivery> getDeliveries() {
		return deliveries;
	}


	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	
	
	
	
}
