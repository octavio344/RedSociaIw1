/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.negocio1;

import arg.edu.iua.ModeloDAO1.UserDB;
import arg.edu.iua.modelo1.Usuario;
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
        if(u.getUsername()==""){
            throw new InvalidUserException("El campo Username es obligatorio!");
        }
        if(u.getPassword()==""){
            throw new InvalidUserException("El campo password es obligatorio!");
        }
        if(b==false){
            throw new InvalidUserException("El usuario y/o contraseña son incorrectos");
        }
        
        return p;
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
    
    
    
}


