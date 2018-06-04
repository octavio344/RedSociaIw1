/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.ModeloDAO1;

import arg.edu.iua.modelo1.Usuario;
import java.util.ArrayList;

/**
 *
 * @author curso
 */
public class UserDB {
    ArrayList<Usuario> lu=new ArrayList<Usuario>();

    public ArrayList<Usuario> getUdb() {
        return lu;
    }
    
    public Usuario devolverUs(int ind){
        return lu.get(ind);
    }
    
    public void añadirU(Usuario u){
        lu.add(u);
    }
    
    public UserDB(){
        lu.add(new Usuario("octi99","123",null));
    }
    
    public Usuario buscarUs(String usr){
        for(int i=0;i<lu.size();i++){
            if(lu.get(i).getUsername().equals(usr)==true){
                return lu.get(i);
            }
        }
        return null;
    }
    
}
