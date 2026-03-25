package edu.ucam.tags;

import java.util.Hashtable;

import edu.ucam.config.Attributes;
import edu.ucam.domain.User;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTagSupport;

public class ListUsers extends BodyTagSupport{
	private static final long serialVersionUID = 1L;

	// HACER LOGICA PARA IMPRIMIR USUARIOS CON SUS OPCIONES DE MODIFICAR Y ELIMINAR
	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		try {
			Hashtable <String, User> users = (Hashtable <String, User>) 
					pageContext.getServletContext().getAttribute(Attributes.USUARIOS);
			
			if(users != null) {
				if(users.size() > 0) {
					for(User u : users.values()) {
						pageContext.getOut().println("<p>NOMBRE: " + u.getUsername() + " | CONTRASEÑA: " + u.getPassword()
						+ " | TIPO: " + u.getType() + "</p>");
					}
				} else {
					pageContext.getOut().print("<p>Lista vacía!</p>");
				}
			} else {
				pageContext.getOut().print("<p>Lista no disponible!</p>");
			}
		} catch(Exception ex) {
			System.out.println("ListUsers -> " + ex.getMessage());
		}
		
		return SKIP_BODY;
	}
}
