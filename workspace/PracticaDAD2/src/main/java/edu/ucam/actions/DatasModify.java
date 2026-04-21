package edu.ucam.actions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;

import edu.ucam.config.Attributes;
import edu.ucam.config.Parameters;
import edu.ucam.config.UserTypes;
import edu.ucam.domain.Titulation;
import edu.ucam.domain.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DatasModify extends Action{

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTit = request.getParameter(Parameters.ID_TIT);
		String nameTit = request.getParameter(Parameters.NAME_TIT);
		
		String username = request.getParameter(Parameters.USERNAME);
		String password = request.getParameter(Parameters.PASSWORD);
		
		Connection conexion = (Connection) request.getServletContext().getAttribute(Attributes.CONEXION);
		
		try {
			if(idTit != null && nameTit != null) {
				Hashtable <String, Titulation> titulations = (Hashtable <String, Titulation>) request.getServletContext().getAttribute(Attributes.TITULACIONES);
				TituModify(titulations, idTit, nameTit, request, conexion);
			} else if(username != null && password != null) {
				Hashtable <String, User> users = (Hashtable <String, User>) request.getServletContext().getAttribute(Attributes.USUARIOS);
				UserModify(users, username, password, request, conexion);
				request.getRequestDispatcher("/crud/secured/adminIndex.jsp").forward(request, response);
			} else {
				request.setAttribute(Attributes.ERROR_MSG, "Credenciales incorrectas!");
			}
		} catch(Exception ex) {
			request.setAttribute(Attributes.ERROR_MSG, ex.getMessage());
		}
		
		if(!response.isCommitted()) request.getRequestDispatcher("/crud/index.jsp").forward(request, response);
	}

	
	// METODOS
	// BUSCA TITULATION Y LA ELIMINA
	private void TituModify (Hashtable<String, Titulation> titulations, String id, String name, HttpServletRequest request, Connection conexion) {
		if(titulations.containsKey(id)) {
			titulations.get(id).setNombre(name);
			
			try {
				// INSERTAR EN LA BASE DE DATOS
				try (PreparedStatement psUpdateTitu = conexion.prepareStatement
						("UPDATE Titulations SET nombre = ? WHERE id = ?")) {
					psUpdateTitu.setString(1, name);
					psUpdateTitu.setString(2, id);
					
					psUpdateTitu.executeUpdate();
				}
			} catch(SQLException ex) {
				request.setAttribute(Attributes.ERROR_MSG, ex.getMessage());
			}
		} else {
			request.setAttribute(Attributes.ERROR_MSG, "No se ha encontrado la titulación");
		}
	}
	
	
	// BUSCA USUARIO Y LA ELIMINA
	private void UserModify(Hashtable <String, User> users, String username, String password, HttpServletRequest request, Connection conexion) {
		if(users.containsKey(username)) {
			User u = users.get(username);
			
			if(u.getUsername().equals("admin") && u.getPassword().equals("admin") && u.getType().equals(UserTypes.ADMIN)) {
				request.setAttribute(Attributes.ERROR_MSG, "Este administrador no se puede modificar!");
			} else {
				users.get(username).setPassword(password);
				
				try {
					// INSERTAR EN LA BASE DE DATOS
					try (PreparedStatement psUpdateTitu = conexion.prepareStatement
							("UPDATE Users SET password = ? WHERE username = ?")) {
						psUpdateTitu.setString(1, password);
						psUpdateTitu.setString(2, username);
						
						psUpdateTitu.executeUpdate();
					}
				} catch(SQLException ex) {
					request.setAttribute(Attributes.ERROR_MSG, ex.getMessage());
				}
			}
		} else {
			request.setAttribute(Attributes.ERROR_MSG, "No se ha encontrado el usuario");
		}
	}
}
