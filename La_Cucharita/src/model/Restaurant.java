package model;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Restaurant {
	
	private List<Employee> worker;
	
	public Restaurant() {
		setWorker(new ArrayList<Employee>());
		worker.add(new Employee("juan","contraseņa","123456",LocalDate.of(2002, 03, 23)));
	}

	public List<Employee> getWorker() {
		return worker;
	}

	public void setWorker(List<Employee> worker) {
		this.worker = worker;
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
	
	
}
