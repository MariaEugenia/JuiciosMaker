package com.Servlet;

import java.sql.*;
import java.util.ArrayList;
//import com.mysql.jdbc.Driver;
import java.util.Date;
import java.util.List;



public class JuicioMaker {

  private Connection connect() {
	  // create a java mysql database connection
	  
	  //LOCAL//
	  
	  //HEROKU//
      
      Connection conn = null;
      try {
          Class.forName(myDriver);
          
          //HEROKU
      } catch (SQLException e) {
          System.out.println(e.getMessage());
      } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return conn;
  }

  /**
   * Update data of a warehouse specified by the id
   *
   * @param id
   * @param name name of the warehouse
   * @param capacity capacity of the warehouse
   */
  public void insertJuicio(String alumno, String mes, String juicio, int grupo, int numero, int nota) {
	// create the java mysql update preparedstatement
      String query = "Insert into Juicios (alumno, mes, juicio, grupo, numero, nota) values (?, ? , ?, ?, ?, ?)";
     
      
      try (Connection conn = this.connect();
		  PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	 
	      preparedStmt.setString   (1, alumno);
	      preparedStmt.setString (2, mes);
	      preparedStmt.setString (3, juicio);
	      preparedStmt.setInt (4, grupo);
	      preparedStmt.setInt (5, numero);
	      preparedStmt.setInt (6, nota);
	      
          // update 
	      preparedStmt.executeUpdate();
	      conn.close();

      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  	}
  
  public void updateJuicio(String alumno, String mes, String juicio, int nota) {
	// create the java mysql update preparedstatement
      String query = "Update Juicios set juicio = ?, nota = ? where mes = ? and alumno = ?";
     
      System.out.println("en update "+ alumno+" "+mes+" "+juicio);
      
      try (Connection conn = this.connect();
		  PreparedStatement preparedStmt = conn.prepareStatement(query)){
    	 
	      preparedStmt.setString   (1, juicio);
	      preparedStmt.setInt   (2, nota);
	      preparedStmt.setString (3, mes);
	      preparedStmt.setString (4, alumno);
	      
          // update 
	      preparedStmt.executeUpdate();
	      conn.close();

      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
  	}
  
  public List<Juicio> getJuiciosForMonth(String mes, int grupo) {
	// create the java mysql update preparedstatement
      String query = "Select alumno, juicio, numero, nota From Juicios where mes = ? and grupo = ? order by numero asc";
     
      List<Juicio> lista = new ArrayList<Juicio>();
      
      try (Connection conn = this.connect();
		  PreparedStatement preparedStmt = conn.prepareStatement(query)){

	      preparedStmt.setString(1, mes);
	      preparedStmt.setInt(2, grupo);
          // update 
	      ResultSet rs = preparedStmt.executeQuery();
	      
	      while (rs.next()) {
	    	  lista.add(new Juicio(rs.getString("alumno"),mes,rs.getString("juicio"),grupo, rs.getInt("numero"), rs.getInt("nota")));
	      }
	      conn.close();

      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
      
      return lista;
	      
  	}
  
  public List<Juicio> getJuiciosForStudent(String alumno) {
		// create the java mysql update preparedstatement
	      String query = "Select mes, juicio, grupo, nota, numero From Juicios where alumno = ?";
	     
	      List<Juicio> lista = new ArrayList<Juicio>();
	      
	      try (Connection conn = this.connect();
			  PreparedStatement preparedStmt = conn.prepareStatement(query)){

		      preparedStmt.setString(1, alumno);
	          // update 
		      ResultSet rs = preparedStmt.executeQuery();
		      
		      while (rs.next()) {
		    	  lista.add(new Juicio(alumno,rs.getString("mes"),rs.getString("juicio"),rs.getInt("grupo"),rs.getInt("numero"), rs.getInt("nota")));
		      }
		      conn.close();

	      } catch (SQLException e) {
	          System.out.println(e.getMessage());
	      }
	      
	      return lista;
		      
	  	}
  
  public boolean existsJuicio(String mes, String alumno) {
	// create the java mysql update preparedstatement
      String query = "Select juicio From Juicios where alumno = ? and mes = ?";
     
      boolean exists = false;
      try (Connection conn = this.connect();
		  PreparedStatement preparedStmt = conn.prepareStatement(query)){

	      preparedStmt.setString(1, alumno);
	      preparedStmt.setString(2, mes);
          // update 
	      ResultSet rs = preparedStmt.executeQuery();
	      while (rs.next()) {
	    	  exists = true;
	      }
	      conn.close();

      } catch (SQLException e) {
          System.out.println(e.getMessage());
      }
      
      return exists;
	      
  	}
  
  public int logIn(String user, String pass) {
		// create the java mysql update preparedstatement
	      String query = "Select id From User where user = ? and password = ?";
	     
	      int id = -1;
	      try (Connection conn = this.connect();
			  PreparedStatement preparedStmt = conn.prepareStatement(query)){

		      preparedStmt.setString(1, user);
		      preparedStmt.setString(2, pass);
	          // update 
		      ResultSet rs = preparedStmt.executeQuery();
		      while (rs.next()) {
		    	  id = rs.getInt("id");
		      }
		      conn.close();

	      } catch (SQLException e) {
	          System.out.println(e.getMessage());
	      }
	      
	      return id;
		      
	  	}
  		

  /**
   * @param args the command line arguments
   */
  public static void main (String[] args) {
      
      // update the warehouse with id 3
      
      //app.insertUser( mail, name, password);
      //System.out.println(app.checkUserExists("test2@gmail.com"));
      //System.out.println(app.checkUserPassword("test2@gmail.com","test2"));
      //app.updateUserPasword( "test2@gmail.com", "test2");
  }
}
