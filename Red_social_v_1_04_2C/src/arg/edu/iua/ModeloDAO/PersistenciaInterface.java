/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.ModeloDAO;

import arg.edu.iua.ModeloDAO.exceptions.NoUserException;
import arg.edu.iua.ModeloDAO.exceptions.PersistenceException;
import arg.edu.iua.modelo.Usuario;

/**
 *
 * @author Octavio
 */
public interface PersistenciaInterface {
    
    public Usuario searchUser(String username) throws NoUserException,PersistenceException;
    
}
