package model;

import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class Restaurant {
	
	private List<Employee> worker;
	
	public Restaurant() {
		setWorker(new ArrayList<Employee>());
		worker.add(new Employee("juan","contraseña","123456",LocalDate.of(2002, 03, 23)));
	}

	public List<Employee> getWorker() {
		return worker;
	}

	public void setWorker(List<Employee> worker) {
		this.worker = worker;
	}
	
	
}
