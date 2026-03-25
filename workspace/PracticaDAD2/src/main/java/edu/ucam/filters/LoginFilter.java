package edu.ucam.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/crud/*")
public class LoginFilter extends HttpFilter implements Filter{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			// PONER EL FILTRO CORRESPONDIENTE (SANTIAGO)
			
			chain.doFilter(request, response);
			return;
		} catch(Exception ex) {
			System.out.println("LoginFilter -> " + ex.getMessage());
		}
		
		if(!response.isCommitted()) request.getRequestDispatcher("login.jsp").forward(request, response);
	}
}
