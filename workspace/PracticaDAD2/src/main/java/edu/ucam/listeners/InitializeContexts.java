package edu.ucam.listeners;

import java.util.Hashtable;

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
		
		Admin admin = new Admin("admin", "admin");
		Student student = new Student("alumno1", "pass");
		Titulation tit = new Titulation("0", "TITULACION 0");
		
		usuarios.put("0_" + admin.getType(), admin);
		usuarios.put("0_" + student.getType(), student);
		titulaciones.put(tit.getId(), tit);
		
		sce.getServletContext().setAttribute(Attributes.USUARIOS, usuarios);
		sce.getServletContext().setAttribute(Attributes.USUARIOS, titulaciones);
	}
}
