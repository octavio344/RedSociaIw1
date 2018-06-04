package arg.edu.iua.app1;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author curso
 */
public class Conexion {
 	/*
		Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/practico","root","root");
		*/
    public static void main(String args[]) throws SQLException, ClassNotFoundException{
		
		Connection c=getConnection();
		DatabaseMetaData dbmd=c.getMetaData();
		
		
		ResultSet rs=dbmd.getCatalogs();
		
		System.out.println();
		System.out.println("Bases de datos: \n");
		
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
	}

	

	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/practico","root","root");
		
	}
	
}
