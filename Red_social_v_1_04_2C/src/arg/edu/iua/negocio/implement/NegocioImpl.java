/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.negocio.implement;

import arg.edu.iua.ModeloDAO.Implements.UserBDImp;
import arg.edu.iua.ModeloDAO.exceptions.NoUserException;
import arg.edu.iua.ModeloDAO.exceptions.PersistenceException;
import arg.edu.iua.modelo.Usuario;
import arg.edu.iua.modelo.exception.NoDataFoundException;
import arg.edu.iua.modelo.util.ErrorDeSintaxis;
import arg.edu.iua.negocio.NegocioInterface;
import arg.edu.iua.negocio.exceptions.BadCredentialsException;
import arg.edu.iua.negocio.exceptions.NoPasswordMatchException;
import arg.edu.iua.negocio.exceptions.ServicesException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Octavio
 */
public class NegocioImpl implements NegocioInterface{
    private UserBDImp db=new UserBDImp();
    
    
    
    @Override
    public Usuario iniciarSecion(String username, String password) throws BadCredentialsException, ServicesException {
        try {
            Usuario u=db.searchUser(username);
            if(u.getPassword().equals(password)){
                return u;
            }
            else{
                throw new BadCredentialsException();
            }
        } catch (NoUserException ex) {
                throw new BadCredentialsException();
        } catch (PersistenceException ex) {
            throw new ServicesException(ex);
        }
    }

    @Override
    public boolean existeUsuario(String username) throws ServicesException {
        try {
           Usuario u=db.searchUser(username);
           return true;
        } catch (NoUserException ex) {
           return false;
        } catch (PersistenceException ex) {
         throw new ServicesException();
        }
    }

    @Override
    public boolean registrarUsuario(Usuario u,String cpass) throws ServicesException, NoDataFoundException,NoPasswordMatchException {
        boolean b=false;
       ErrorDeSintaxis confi=u.chequeaCampos(cpass);
       if(confi.equals(null)){
           if(u.getPassword().equals(cpass)){
               String str="insert into usuario (Nombre,Apellido,Username,Contraseña,Fecha_nacimiento) values (?,?,?,?,?)";
               try {
                   SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
                  String fnac= dateformat.format(u.getFechaNac());
                   PreparedStatement ps=db.getCon().prepareStatement(str);
                   ps.setString(1,u.getNombre());
                   ps.setString(2,u.getApellido());
                   ps.setString(3, u.getUsername());
                   ps.setString(4, u.getPassword());
                   ps.setString(5, fnac );
                   b=true;
               } catch (SQLException ex) {
                   throw new ServicesException("Error en el motor Mysql");
               }
               
               return b;
           }else{
               throw new NoPasswordMatchException("Las contraseñas no coinciden");
           }
           
       }else{
           throw new NoDataFoundException(String.format(confi.getCampo(),"El campo %s es obligatorio"));
       }
    }
    
}
