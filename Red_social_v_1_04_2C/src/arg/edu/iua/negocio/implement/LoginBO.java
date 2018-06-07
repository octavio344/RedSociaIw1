/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.negocio.implement;

import arg.edu.iua.ModeloDAO.UserDB;
import arg.edu.iua.modelo.Usuario;
import arg.edu.iua.presentacion.Myconecction;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Octavio
 */
public class LoginBO {
    UserDB lc=new UserDB();
    
    
    public void registrarUsuario(Usuario u) throws InvalidUserException, InvalidPasswordException{
        if(lc.buscarUs(u.getUsername())==null){
            throw new InvalidUserException("El username "+u.getUsername()+" ya esta registrado");
        }
        if(u.getUsername()==null){
            throw new InvalidUserException();
        }
        if(u.getPassword()==null){
            throw new InvalidPasswordException("El campo password es obligatorio!");
        }
        if(u.getNombre()==null){
            throw new InvalidUserException("El campo nombre es obligatorio");
        }
        if(u.getApellido()==null){
            throw new InvalidUserException("El campo apellido es obligatorio");
        }
        if(u.getFechaNac()==null){
            throw new InvalidUserException("El campo edad es obligatorio");
        }
        else{
            lc.añadirU(u);
        }
    }

    public Usuario iniciarSecion(Usuario u) throws InvalidUserException{
        boolean b=false;
        Usuario p=new Usuario();
        p.setPassword(null);
        p.setUsername(null);
        for(int i=0;i<lc.getUdb().size();i++){
            if(lc.devolverUs(i).equals(u)==true){
                b=true;
               p=lc.devolverUs(i);
            }
        }
        if(u.getUsername().equals("")){
            throw new InvalidUserException("El campo Username es obligatorio!");
        }
        if(u.getPassword().equals("")){
            throw new InvalidUserException("El campo password es obligatorio!");
        }
        if(b==false){
            throw new InvalidUserException("El usuario y/o contraseña son incorrectos");
        }
        
        return p;
    }
    
    public void actulizarDB() throws SQLException{
        PreparedStatement ps;
        ResultSet rs;
        boolean b=false;
        
       String query= "SELECT * FROM usuario";
        ps= Myconecction.getConexion().prepareStatement(query);
        rs= ps.executeQuery();
        ResultSetMetaData metadata=rs.getMetaData();
        int colc=metadata.getColumnCount();
       
        //El siguiente bloque recorre el result set hasta el final es decir cada usuario de la bd y asigna los atributos a un objeto de tipo usuario
        while(rs.next()){
            
           Usuario u=new Usuario();
                
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setUsername(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setFechaNac(rs.getDate(6));
                
             
                
            /*//algoritmo usado para comprobaciones System.out.println("-----------");
                 for(int j=0;j<lc.getUdb().size();j++){
                System.out.println(lc.devolverUs(j).getUsername());
                }*/
                 
            //En este fro recorremos la lista de usuarios que tenemos en el programa y verificamos que ya no esten en memoria en caso contrario los agregamos
            for(int j=0;j<lc.getUdb().size();j++){
                    if(lc.devolverUs(j).getUsername().equals(u.getUsername())==true){
                       b=true;
                    }
                }
           if(b==false){//Este if es para que agrege el usuario si no esta en la lista
                    lc.añadirU(u); 
                } 
           b=false;
        }
    }

 
        
    
    public static class InvalidPasswordException extends Exception {

        public InvalidPasswordException(String msj){
            super(msj);
        }
    }

    public static class InvalidUserException extends Exception {

        public InvalidUserException(String msj) {
            super(msj);
        }

        private InvalidUserException() {
         this("El campo usuario es obligatorio!");   
        }
    }
    
    public ArrayList<Usuario> devolverLista(){
        return lc.getUdb();
    }
    
    
}


