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
import arg.edu.iua.presentacion.Context;
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
        Context  con;

    public UserBDImp() {
    }
    
        
        
    public UserBDImp(Context con){
       this.con=con;
    }
    
    @Override
    public Usuario searchUser(String username) throws NoUserException, PersistenceException {
      Usuario u=new Usuario();
      u=null;
      
      String str="select * from usuario where username=?";
      PreparedStatement ps;
      ResultSet rs;
      
        try {
            
            System.out.println("1 " + con);
            System.out.println("2 " + con.getCon());
            System.out.println("3 " + str);
            ps=con.getCon().prepareStatement(str);
            ps.setString(1, username);
            rs=ps.executeQuery();
            if(rs.next()){
                return rsUsuario(rs);
            }else{
                str="select * from usuario where email=?";
                ps=con.getCon().prepareStatement(str);
                ps.setString(1, username);
                rs=ps.executeQuery();
                if(rs.next()){
                    return rsUsuario(rs);
                }
                else{
                    throw new NoUserException(String.format("El usuario/email %s no existe", username));
                }
                
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

    @Override
    public String registrarU(Usuario u) throws SQLException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
