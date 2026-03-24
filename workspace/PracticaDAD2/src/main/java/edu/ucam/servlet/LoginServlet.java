package edu.ucam.servlet;

import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.domain.Constantes;
import edu.ucam.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter(Constantes.ATR_USERNAME);
        String password = request.getParameter(Constantes.ATR_PASSWORD);

        Hashtable<String, User> usuarios =
            (Hashtable<String, User>) request.getServletContext().getAttribute(Constantes.CTX_USUARIOS);

        User u = usuarios.get(username);

        if (u != null && u.getPassword().equals(password)) {
            request.getSession().setAttribute(Constantes.SESION_USUARIO , u);
            response.sendRedirect("secured/index.jsp");
        } else {
            request.setAttribute("ERROR", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}