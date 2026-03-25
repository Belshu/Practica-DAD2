package edu.ucam.filters;

import java.io.IOException;

import edu.ucam.config.Attributes;
import edu.ucam.domain.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/crud/secured/*")
public class AdminFilter extends HttpFilter implements Filter{
	private static final long serialVersionUID = 1L;
	
	/**
	 * FILTRA USUARIOS POR EL TIPO, SI SON ADMINISTRADORES O NO.
	 * */
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			User user = (User) request.getSession().getAttribute(Attributes.LOGGED_USER);
			
			if(user != null && user.getType().equals("ADMIN")) {
				System.out.println("AdminFilter -> Administrador '" + user.getUsername() + "': entrado en secured");
				chain.doFilter(request, response);
				return;
			} else {
				System.out.println("AdminFilter -> Usuario '" + user.getUsername() + "': acceso no permitido");
			}
		} catch(Exception ex) {
			System.out.println("AdminFilter -> " + ex.getMessage());
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
