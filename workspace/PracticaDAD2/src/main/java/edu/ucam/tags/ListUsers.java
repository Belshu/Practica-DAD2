package edu.ucam.tags;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTagSupport;

public class ListUsers extends BodyTagSupport{
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		// HACER LOGICA PARA IMPRIMIR USUARIOS CON SUS OPCIONES DE MODIFICAR Y ELIMINAR
		
		return super.doStartTag();
	}
}
