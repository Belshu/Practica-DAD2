package edu.ucam.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.config.Attributes;
import edu.ucam.config.Parameters;
import edu.ucam.domain.User;

/**
 * LOGIN SERVLET
 * COMPRUEBA LAS CREDENCIALES DEL USUARIO Y LO REDIRIGE SEGÚN SU TIPO (ADMIN / STUDENT)
 */

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// OBTENER PARÁMETROS DEL FORMULARIO
			String username = request.getParameter(Parameters.USERNAME);
			String password = request.getParameter(Parameters.PASSWORD);

			// OBTENER LISTA DE USUARIOS DEL CONTEXTO
			Hashtable<String, User> usuarios =
					(Hashtable<String, User>) request.getServletContext().getAttribute(Attributes.USUARIOS);

			// BUSCAR USUARIO
			User user = usuarios.get(username);

			// VALIDAR CREDENCIALES
			if (user != null && user.getPassword().equals(password)) {

				// GUARDAR USUARIO EN SESIÓN
				request.getSession().setAttribute(Attributes.LOGGED_USER, user);

				// REDIRECCIÓN SEGÚN TIPO DE USUARIO
				if (user.getType().equals("ADMIN")) {
					response.sendRedirect("crud/secured/adminIndex.jsp");
				} else {
				response.sendRedirect("crud/index.jsp");
				}
			return;
			}

			// SI FALLA EL LOGIN -> MENSAJE DE ERROR
			request.setAttribute(Attributes.ERROR_MSG, "Credenciales incorrectas");
			request.getRequestDispatcher("login.jsp").forward(request, response);

		} catch(Exception ex) {
			System.out.println("LoginServlet -> " + ex.getMessage());
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
