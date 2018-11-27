package com.Servlet;

public class Juicio {
	String alumno;
	String mes;
	String juicio;
	int grupo;
	int numero;
	int nota;


	public Juicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Juicio(String alumno, String mes, String juicio, int grupo, int numero, int nota) {
		super();
		this.alumno = alumno;
		this.mes = mes;
		this.juicio = juicio;
		this.grupo = grupo;
		this.numero = numero;
		this.nota = nota;
	}
	
	public String getAlumno() {
		return alumno;
	}
	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getJuicio() {
		return juicio;
	}
	public void setJuicio(String juicio) {
		this.juicio = juicio;
	}
	
	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	
	
}
