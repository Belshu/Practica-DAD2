package edu.ucam.listeners;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.ucam.config.Attributes;
import edu.ucam.domain.Admin;
import edu.ucam.domain.Student;
import edu.ucam.domain.Titulation;
import edu.ucam.domain.User;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitializeContexts implements ServletContextListener{

	/**
	 * MÁS ADELANTE: GUARDAR EN BBDD LOS HASHTABLE
	 * */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("InitializeContexts -> destruyendo contexto...");
	}

	
	/**
	 * INICIALIZACIÓN DE HASHTABLES Y ASIGNACIÓN DE SUS ATRIBUTOS. TAMBIÉN AÑADIDO UN EJEMPLO DE CADA UNO
	 * */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("InitializeContexts -> Iniciando contextos...");
		
		Hashtable <String, User> usuarios = new Hashtable<>();
		Hashtable <String, Titulation> titulaciones = new Hashtable<>();
		
		/* PONER USUARIOS MEDIANTE CÓDIGO --------------------------------------------------------- 
		Admin admin = new Admin("admin", "admin");
		Student student = new Student("alumno1", "pass");
		Titulation tit = new Titulation("0", "TITULACION 0");
		
		usuarios.put(admin.getUsername(), admin);
		usuarios.put(student.getUsername(), student);
		titulaciones.put(tit.getId(), tit);
		titulaciones.put("1", new Titulation("1", "TITULACION 1"));
		titulaciones.put("2", new Titulation("2", "TITULACION 2"));
		titulaciones.put("3", new Titulation("3", "TITULACION 3"));
		*/
		
		// INICIALIZAR USUARIOS DE BBDD --------------------------------------------------------- 
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/dad2_24420162G_48845233H");
			
			Connection conexion = ds.getConnection();
			
			PreparedStatement ps = conexion.prepareStatement("SELECT * FROM Users");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {				
				if(rs.getString("type").equals("ADMIN")) {
					usuarios.put(rs.getString("username"), new Admin(rs.getString("username"), rs.getString("password")));
				} else if(rs.getString("type").equals("STUDENT")) {
					usuarios.put(rs.getString("username"), new Student(rs.getString("username"), rs.getString("password")));
				}
			}
			
			sce.getServletContext().setAttribute("CONEXION", conexion);
		} catch(NamingException ex) {
			System.out.println("InitializeContext -> " + ex.getMessage());
		} catch(SQLException ex) {
			System.out.println("InitializeContext -> " + ex.getMessage());
		}
		
		sce.getServletContext().setAttribute(Attributes.USUARIOS, usuarios);
		sce.getServletContext().setAttribute(Attributes.TITULACIONES, titulaciones);
	}
}
