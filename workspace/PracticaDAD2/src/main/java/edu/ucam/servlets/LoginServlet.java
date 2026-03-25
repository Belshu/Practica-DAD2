package edu.ucam.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.config.Attributes;
import edu.ucam.config.Parameters;
import edu.ucam.domain.Admin;
import edu.ucam.domain.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int sessionCount;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		sessionCount = 0;
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Hashtable <String, User> users = (Hashtable <String, User>) 
					request.getServletContext().getAttribute(Attributes.TITULACIONES);
			
			User u = new Admin(request.getParameter(Parameters.USERNAME), request.getParameter(Parameters.USERNAME));
			
			request.getSession().setAttribute(Attributes.LOGGED_USER, u);
			request.getRequestDispatcher("/crud/index.jsp").forward(request, response);
		} catch(Exception ex) {
			System.out.println("LoginServlet -> " + ex.getMessage());
		}
		
		if(!response.isCommitted()) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void updateCounter(HttpServletRequest request) {
		if(request.getServletContext().getAttribute(Attributes.COUNTER) != null) {
			sessionCount = (int) request.getServletContext().getAttribute(Attributes.COUNTER);
		}
		sessionCount++;
		request.getServletContext().setAttribute(Attributes.COUNTER, sessionCount);
	}
}
