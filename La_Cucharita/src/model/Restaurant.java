package model;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Restaurant {
	
	private List<Employee> worker;
	private List <Ingredient> ingredients;
	private List<Dish> menu;
	
	
	public Restaurant() {
		setWorker(new ArrayList<Employee>());
		ingredients= new ArrayList<Ingredient>();
		menu = new ArrayList<Dish>();

		ingredients.add(new Ingredient("Lentejas",800,"g"));
		worker.add(new Employee("juan","contraseña","123456",LocalDate.of(2002, 03, 23)));
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
	
	public void addCombo(String name, String ingredient, double quantity, String measure ) {
		boolean found = false;
		int position = 0;
		for(int i=0; i<menu.size() && found==false; i++) {
			if(name.equals(menu.get(i).getName())){
				found = true;
				position = i;								
			}					
		}
		menu.get(position).addRecipe(ingredient, quantity, measure);
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
	
	
	
}
