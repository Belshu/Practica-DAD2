package edu.ucam.tags;

import java.util.Hashtable;

import edu.ucam.config.Attributes;
import edu.ucam.domain.Titulation;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTagSupport;

public class ListTitulations extends BodyTagSupport{
	private static final long serialVersionUID = 1L;

	// HACER LOGICA PARA IMPRIMIR TITULACIONES CON SUS OPCIONES DE MODIFICAR Y ELIMINAR 
	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		try {
			Hashtable <String, Titulation> titulations = (Hashtable <String, Titulation>)
					pageContext.getServletContext().getAttribute(Attributes.TITULACIONES);
			
			if(titulations != null) {
				if(titulations.size() > 0) {
					String contextPath = pageContext.getRequest().getServletContext().getContextPath();
					for(Titulation t : titulations.values()) {
						pageContext.getOut().println("<p>ID: " + t.getId() + " | NOMBRE: " + t.getNombre() + "</p>");
					}
				} else {
					pageContext.getOut().println("Lista vacía!");
				}
			} else {
				pageContext.getOut().println("Lista no disponible!");
			}
		} catch(Exception ex) {
			System.out.println("ListTitulations -> " + ex.getMessage());
		}
		
		return SKIP_BODY;
	}
}
