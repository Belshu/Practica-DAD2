package edu.ucam.actions;

import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.config.Attributes;
import edu.ucam.config.Parameters;
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
		
		try {
			if(idTit != null && nameTit != null) {
				Hashtable <String, Titulation> titulations = (Hashtable <String, Titulation>) request.getServletContext().getAttribute(Attributes.TITULACIONES);
				TituModify(titulations, idTit, nameTit, request);
			} else if(username != null && password != null) {
				Hashtable <String, User> users = (Hashtable <String, User>) request.getServletContext().getAttribute(Attributes.USUARIOS);
				UserModify(users, username, password, request);
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
	
	// BUSCA TITULATIÓN Y LA ELIMINA
	private void TituModify (Hashtable<String, Titulation> titulations, String id, String name, HttpServletRequest request) {
		if(titulations.containsKey(id)) {
			titulations.get(id).setNombre(name);
		} else {
			request.setAttribute(Attributes.ERROR_MSG, "No se ha encontrado la titulación");
		}
	}
	
	
	// BUSCA USUARIO Y LA ELIMINA
	private void UserModify(Hashtable <String, User> users, String username, String password, HttpServletRequest request) {
		if(users.containsKey(username)) {
			users.get(username).setPassword(password);
		} else {
			request.setAttribute(Attributes.ERROR_MSG, "No se ha encontrado el usuario");
		}
	}
}
