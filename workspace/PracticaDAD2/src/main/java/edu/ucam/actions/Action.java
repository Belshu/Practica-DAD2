package edu.ucam.actions;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Action {
	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException ;
}
