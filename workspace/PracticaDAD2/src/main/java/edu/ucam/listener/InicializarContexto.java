package edu.ucam.listener;

import java.util.Hashtable;

import edu.ucam.domain.Constantes;
import edu.ucam.domain.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InicializarContexto implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Hashtable<String, User> usuarios = new Hashtable<>();
        
        usuarios.put("admin", new User("admin", "admin", "admin"));

        usuarios.put("pepe", new User("pepe", "1234", "user"));

        sce.getServletContext().setAttribute(Constantes.CTX_USUARIOS, usuarios);
    }
}