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

public class DatasRemove extends Action {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTit = request.getParameter(Parameters.ID_TIT);
		String username = request.getParameter(Parameters.USERNAME);
		
		Connection conexion = (Connection) request.getServletContext().getAttribute(Attributes.CONEXION);
		
		try {
			if(idTit != null) {
				Hashtable <String, Titulation> titulations = (Hashtable <String, Titulation>) request.getServletContext().getAttribute(Attributes.TITULACIONES);
				tituRemove(titulations, idTit, request, conexion);
			} else if(username != null) {
				Hashtable <String, User> users = (Hashtable <String, User>) request.getServletContext().getAttribute(Attributes.USUARIOS);
				userRemove(users, username, request, conexion);
				request.getRequestDispatcher("/crud/secured/adminIndex.jsp").forward(request, response);
			}
		} catch(Exception ex) {
			request.setAttribute(Attributes.ERROR_MSG, ex.getMessage());
		}
		
		if(!response.isCommitted()) request.getRequestDispatcher("/crud/index.jsp").forward(request, response);
	}

	
	// METODOS
	
	// BUSCA TITULATIÓN Y LA ELIMINA
	private void tituRemove(Hashtable <String, Titulation> titulations, String id, HttpServletRequest request, Connection conexion) {
		if(titulations.containsKey(id)) {
			titulations.remove(id);
			
			try {
				// INSERTAR EN LA BASE DE DATOS
				try (PreparedStatement psUpdateTitu = conexion.prepareStatement
						("DELETE FROM Titulations WHERE id = ?")) {
					psUpdateTitu.setString(1, id);
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
	private void userRemove(Hashtable <String, User> users, String username, HttpServletRequest request, Connection conexion) {
		if(users.containsKey(username)) {
			User u = users.get(username);
			
			if(u.getUsername().equals("admin") && u.getPassword().equals("admin") && u.getType().equals(UserTypes.ADMIN)) {
				request.setAttribute(Attributes.ERROR_MSG, "Este administrador no se puede eliminar!");
				
			} else {
				users.remove(username);
				
				try {
					// INSERTAR EN LA BASE DE DATOS
					try (PreparedStatement psUpdateTitu = conexion.prepareStatement
							("DELETE FROM Users WHERE username = ?")) {
						psUpdateTitu.setString(1, username);
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
