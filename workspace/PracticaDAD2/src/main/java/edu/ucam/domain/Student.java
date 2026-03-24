package edu.ucam.domain;

public class Student extends User{
	
	public Student(String username, String password) {
		super(username, password, "STUDENT");
	}
}
