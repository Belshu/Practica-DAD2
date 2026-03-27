package edu.ucam.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;

import edu.ucam.actions.Action;
import edu.ucam.actions.DatasAdd;
import edu.ucam.actions.DatasModify;
import edu.ucam.actions.DatasRemove;
import edu.ucam.config.ActionID;
import edu.ucam.config.Attributes;
import edu.ucam.config.Parameters;

/**
 * Servlet implementation class Control
 */
@WebServlet("/Control")
public class Control extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Hashtable <String, Action> actions;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		actions = new Hashtable<String, Action>();
		
		actions.put(ActionID.ADD, new DatasAdd());
		actions.put(ActionID.REMOVE, new DatasRemove());
		actions.put(ActionID.MODIFY, new DatasModify());
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// PONER LÓGICA PARA GESTIONAR TODOS LOS MOVIMIENTOS DE LOS SERVLETS (SALVO LOGIN Y REGISTRY)
		String actionId = request.getParameter(Parameters.ACTION_ID);
		
		if(actionId != null && actions.containsKey(actionId)) {
			actions.get(actionId).execute(request, response);
			return;
		} else {
			request.setAttribute(Attributes.ERROR_MSG, "Acción no pasada!");
		}
		
		request.getRequestDispatcher("/crud/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
