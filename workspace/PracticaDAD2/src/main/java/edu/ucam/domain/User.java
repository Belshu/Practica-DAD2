package edu.ucam.domain;

public class User {
    private String username;
    private String password;
    private String rol; // admin o user

    public User(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getUsername() {
    	return username; 
    	}
    public String getPassword() {
    	return password; 
    	}
    public String getRol() { 
    	return rol; 
    	}
}
