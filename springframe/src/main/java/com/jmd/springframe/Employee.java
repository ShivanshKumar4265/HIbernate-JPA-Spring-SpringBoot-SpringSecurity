package com.jmd.springframe;



public class Employee {
	
	private String name;
	private String email;
	private int id;
	
	public Employee() {
		
	}
	
	public Employee(String name,String email, int id) {
		this.email = email;
		this.id = id;
		this.name = name;
		
	}
	
	@Override
	public String toString() {
		return "name "+ name + " email  " + email + " id " + id;
	}

}




