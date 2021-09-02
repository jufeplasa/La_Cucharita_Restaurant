package model;

import java.time.LocalDate;

public class Employee {
	
	private String name;
	private String password;
	private String id;
	private LocalDate birthday;
	
	public Employee(String name, String password, String id, LocalDate birthday) {
		this.name=name;
		this.password=password;
		this.id=id;
		this.birthday=birthday;
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
	
}
