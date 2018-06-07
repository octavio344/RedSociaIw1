/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.negocio;

import arg.edu.iua.modelo.Usuario;
import arg.edu.iua.modelo.exception.NoDataFoundException;
import arg.edu.iua.negocio.exceptions.BadCredentialsException;
import arg.edu.iua.negocio.exceptions.NoPasswordMatchException;
import arg.edu.iua.negocio.exceptions.ServicesException;

/**
 *
 * @author Octavio
 */
public interface NegocioInterface {
        
    public Usuario iniciarSecion(String username,String password) throws BadCredentialsException,ServicesException;

    public boolean existeUsuario(String username) throws ServicesException;
    
    public boolean registrarUsuario(Usuario u,String cpass) throws ServicesException,NoDataFoundException,NoPasswordMatchException;
}
