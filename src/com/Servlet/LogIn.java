package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet("/login")
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user").trim();
		String pass = request.getParameter("pass").trim();

		String respuesta="Login success";
		JuicioMaker m = new JuicioMaker();
		int id= m.logIn(user,pass);
		if (id==-1) {
			respuesta="Login Failed";
		}
		
		Cookie loginCookie = new Cookie("user",user);
		//setting cookie to expiry in 30 mins
		loginCookie.setMaxAge(30*60);
		response.addCookie(loginCookie);
		response.setContentType("text/plain");
		response.getWriter().write(respuesta);

	}


}

