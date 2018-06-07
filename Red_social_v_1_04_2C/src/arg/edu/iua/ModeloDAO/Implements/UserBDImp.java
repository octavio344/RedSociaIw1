/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.ModeloDAO.Implements;

import arg.edu.iua.ModeloDAO.PersistenciaInterface;
import arg.edu.iua.ModeloDAO.exceptions.NoUserException;
import arg.edu.iua.ModeloDAO.exceptions.PersistenceException;
import arg.edu.iua.modelo.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Octavio
 */
public class UserBDImp implements PersistenciaInterface{
    Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public UserBDImp(Connection con){
        this.con=con;
    }
    
    public UserBDImp(){
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb","root","root");
            this.con=con;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public Usuario searchUser(String username) throws NoUserException, PersistenceException {
      Usuario u=new Usuario();
      u=null;
      
      String str="select * from usuario where username=?";
      PreparedStatement ps;
      ResultSet rs;
      
        try {
            ps=con.prepareStatement(str);
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()){
                return rsUsuario(rs);
            }else{
                throw new NoUserException(String.format("El usuario %s no existe", username));
            }
        } catch (SQLException ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    
    private Usuario rsUsuario(ResultSet rs) throws SQLException{
        Usuario u=new Usuario();
        u.setApellido(rs.getString("Apellido"));
        u.setFechaNac(rs.getDate("Fecha_nacimiento"));
        u.setNombre(rs.getString("Nombre"));
        u.setPassword(rs.getString("Contraseña"));
        u.setUsername(rs.getString("Username"));
        return u;
    }
    
}
