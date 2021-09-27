package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private List<Delivery> request;
	
	private String name;
	private String password;
	private String id;
	private LocalDate birthday;
	
	public Employee(String name, String password, String id, LocalDate birthday) {
		this.name=name;
		this.password=password;
		this.id=id;
		this.birthday=birthday;
		request=new ArrayList<Delivery>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	public String generateReport() {
		int numDelivered=0;
		for(int i=0;i<request.size();i++) {
			if(request.get(i).getDeliveryState()==State.ENTREGADO) {
				numDelivered++;
			}
		}
		return"The employee "+name+" deliver "+numDelivered+" orders and generate $";
	}
	
}
