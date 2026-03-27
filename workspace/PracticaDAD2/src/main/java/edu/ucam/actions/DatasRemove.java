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

public class DatasRemove extends Action {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idTit = request.getParameter(Parameters.ID_TIT);
		String username = request.getParameter(Parameters.USERNAME);
		
		try {
			if(idTit != null) {
				Hashtable <String, Titulation> titulations = (Hashtable <String, Titulation>) request.getServletContext().getAttribute(Attributes.TITULACIONES);
				tituRemove(titulations, idTit, request);
			} else if(username != null) {
				Hashtable <String, User> users = (Hashtable <String, User>) request.getServletContext().getAttribute(Attributes.USUARIOS);
				userRemove(users, username, request);
				request.getRequestDispatcher("/crud/secured/adminIndex.jsp").forward(request, response);
			}
		} catch(Exception ex) {
			request.setAttribute(Attributes.ERROR_MSG, ex.getMessage());
		}
		
		if(!response.isCommitted()) request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	
	// METODOS
	
	// BUSCA TITULATIÓN Y LA ELIMINA
	private void tituRemove(Hashtable <String, Titulation> titulations, String id, HttpServletRequest request) {
		if(titulations.containsKey(id)) {
			titulations.remove(id);
		} else {
			request.setAttribute(Attributes.ERROR_MSG, "No se ha encontrado la titulación");
		}
	}
	
	
	// BUSCA USUARIO Y LA ELIMINA
	private void userRemove(Hashtable <String, User> users, String username, HttpServletRequest request) {
		if(users.containsKey(username)) {
			users.remove(username);
		} else {
			request.setAttribute(Attributes.ERROR_MSG, "No se ha encontrado el usuario");
		}
	}
}
