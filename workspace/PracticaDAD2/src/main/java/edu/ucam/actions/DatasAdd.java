package edu.ucam.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;

import edu.ucam.config.Attributes;
import edu.ucam.config.Parameters;
import edu.ucam.config.UserTypes;
import edu.ucam.domain.Admin;
import edu.ucam.domain.Student;
import edu.ucam.domain.Titulation;
import edu.ucam.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DatasAdd extends Action {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idTit = request.getParameter(Parameters.ID_TIT);
			String nameTit = request.getParameter(Parameters.NAME_TIT);
			
			String username = request.getParameter(Parameters.USERNAME);
			String password = request.getParameter(Parameters.PASSWORD);
			String type = request.getParameter(Parameters.USERTYPE);
			
			Connection conexion = (Connection) request.getServletContext().getAttribute(Attributes.CONEXION);
			
			// SI ES NUEVO USUARIO -> REDIRIGE A adminIndex.jsp
			// SI ES CUALQUIER OTRO DATO U ERRORES -> index.jsp
			if(idTit != null && nameTit != null) {
				Hashtable <String, Titulation> titulations = (Hashtable <String, Titulation>) request.getServletContext().getAttribute(Attributes.TITULACIONES);
				newTitu(titulations, idTit, nameTit, request, conexion);
			} else if(username != null && password != null && type != null){
				Hashtable <String, User> users = (Hashtable <String, User>) request.getServletContext().getAttribute(Attributes.USUARIOS);
				newUser(users, username, password, type, request, conexion);
				
				request.getRequestDispatcher("/crud/secured/adminIndex.jsp").forward(request, response);
				return;
			} else {
				request.setAttribute(Attributes.ERROR_MSG, "Campos no válidos!");
			}
;		} catch(Exception ex) {
			request.setAttribute(Attributes.ERROR_MSG, ex.getMessage());
		}
		
		if(!response.isCommitted()) request.getRequestDispatcher("/crud/index.jsp").forward(request, response);
	}
	
	
	// METODOS
	
	// CREAR NUEVA TITULACION Y AÑADIRLO A LA HASHTABLE
	private void newTitu(Hashtable <String, Titulation> titulations, String id, String name, HttpServletRequest request, Connection conexion) {
		if(titulations.containsKey(id)) {
			request.setAttribute(Attributes.ERROR_MSG, "La titulación ya existe");
			return;
		}
		
		titulations.put(id, new Titulation(id, name));
		try {
			// INSERTAR EN LA BASE DE DATOS
			try (PreparedStatement psInsertTitu = conexion.prepareStatement
					("INSERT INTO Titulations (id, nombre) VALUES (?, ?)")) {
				psInsertTitu.setString(1, id);
				psInsertTitu.setString(2, name);
				
				psInsertTitu.executeUpdate();
			}
		} catch(SQLException ex) {
			request.setAttribute(Attributes.ERROR_MSG, ex.getMessage());
		}
	}
	
	
	// CREAR NUEVO USUARIO Y AÑADIRLO A LA HASHTABLE
	private void newUser(Hashtable <String, User> users, String username, String password, String type, HttpServletRequest request, Connection conexion) {
		if(users.contains(username)) {
			request.setAttribute(Attributes.ERROR_MSG, "El usuario ya existe");
			return;
		}
		
		User user = null;
		
		if(type.equals(UserTypes.ADMIN)) {
			user = new Admin(username, password);
		} else if (type.equals(UserTypes.STUDENT)) {
			user = new Student(username, password);
		} else {
			request.setAttribute(Attributes.ERROR_MSG, "Tipo de usuario no válido");
			return;
		}
		
		users.put(username, user);
		
		try {
			// INSERTAR EN LA BASE DE DATOS
			try (PreparedStatement psInsertUser = conexion.prepareStatement
					("INSERT INTO Users (username, password, type) VALUES (?, ?, ?)")) {
				psInsertUser.setString(1, username);
				psInsertUser.setString(2, password);
				psInsertUser.setString(2, type);
				
				psInsertUser.executeUpdate();
			}
		} catch(SQLException ex) {
			request.setAttribute(Attributes.ERROR_MSG, ex.getMessage());
		}
	}
}
