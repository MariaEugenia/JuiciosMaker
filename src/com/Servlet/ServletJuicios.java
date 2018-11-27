package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet("/ServletJuicios")
public class ServletJuicios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mes = request.getParameter("mes").trim();
		
		int grupo = Integer.parseInt(request.getParameter("grupo"));
		
		JuicioMaker m = new JuicioMaker();
		List<Juicio> lista = m.getJuiciosForMonth(mes, grupo);
		
		JSONArray jsonarr =new JSONArray();
		Iterator<Juicio> iteratorQ = lista.iterator();
		while(iteratorQ.hasNext()) {
			Juicio tmp = iteratorQ.next();
			JSONObject obj = new JSONObject();
			obj.put("alumno", tmp.getAlumno());	    	
			obj.put("mes", tmp.getMes());
			obj.put("juicio", tmp.getJuicio());
			obj.put("nota", tmp.getNota());
			jsonarr.put(obj);
		}
		
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		//response.getWriter().write(estates.get(1));
		PrintWriter out = response.getWriter();
		out.print(jsonarr);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		if (request.getParameter("editar") != null) {
			String juicio = request.getParameter("jucio").trim();
			String alumno = request.getParameter("alumno").trim();
			String mes = request.getParameter("mes").trim();
			int nota = Integer.parseInt(request.getParameter("nota"));
			JuicioMaker m = new JuicioMaker();
			m.updateJuicio(alumno, mes, juicio, nota);
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("Juicio editado correctamente");
		}if (request.getParameter("juiciosalumno") != null) {
			String alumno = request.getParameter("alumno").trim();
			//int grupo = Integer.parseInt(request.getParameter("grupo"));

			JuicioMaker m = new JuicioMaker();
			List<Juicio> lista = m.getJuiciosForStudent(alumno);
			
			JSONArray jsonarr =new JSONArray();
			Iterator<Juicio> iteratorQ = lista.iterator();
			while(iteratorQ.hasNext()) {
				Juicio tmp = iteratorQ.next();
				JSONObject obj = new JSONObject();
				obj.put("alumno", tmp.getAlumno());	    	
				obj.put("mes", tmp.getMes());
				obj.put("juicio", tmp.getJuicio());
				obj.put("nota", tmp.getNota());
				obj.put("numero", tmp.getNumero());
				jsonarr.put(obj);
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//response.getWriter().write(estates.get(1));
			PrintWriter out = response.getWriter();
			out.print(jsonarr);
		}else {
			String juicio = request.getParameter("jucio").trim();
			String alumno = request.getParameter("alumno").trim();
			String mes = request.getParameter("mes").trim();
			int grupo = Integer.parseInt(request.getParameter("grupo"));
			int numero = Integer.parseInt(request.getParameter("numero"));
			int nota = Integer.parseInt(request.getParameter("nota"));

			JuicioMaker m = new JuicioMaker();
			m.insertJuicio(alumno, mes, juicio, grupo, numero, nota);
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("Juicio creado correctamente");
		}
		


	}


}

