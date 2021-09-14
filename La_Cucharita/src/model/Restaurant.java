package model;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Restaurant {
	
	private List<Employee> worker;
	private List <Ingredient> ingredients;
	
	public Restaurant() {
		setWorker(new ArrayList<Employee>());
		ingredients= new ArrayList<Ingredient>();

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

	
	public boolean addIngrendient(Ingredient newIngredient) {
		boolean condition=false;
		if (ingredients.add(newIngredient)) {
			 condition=true;
		}
		/*for (int i=0; i<ingredients.size();i++) {
			if (name.equalsIgnoreCase(ingredients.get(i).getName())&& measure.equalsIgnoreCase(ingredients.get(i).getMeasure())){
				ingredients.get(i).editAmount(amount);
				condition=false;
				return condition;
			}
		}*/
		
		return condition;
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
