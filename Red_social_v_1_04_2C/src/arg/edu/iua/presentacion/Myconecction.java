/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.presentacion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Octavio
 */
public class Myconecction {
    
    
    public static Connection getConexion(){
     Connection con=null;   
     try{
         Class.forName("com.mysql.jdbc.Driver");
         con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb","root","root");
     }catch(Exception e){
         System.out.println(e.getMessage());
     }
     return con;
    }
}
