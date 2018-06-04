/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.modelo1;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author curso
 */
public class Usuario extends Perfil {
    String username;
    String password;
    Perfil pusuaruio;
    ArrayList<Contacto> lc=new ArrayList<Contacto>();

    public ArrayList<Contacto> getContactos() {
        return lc;
    }

    
    public void añdirContacto(Contacto c){
        lc.add(c);
    }

    public Contacto devolverCont(String usr){
        for(int i=0;i<lc.size();i++){
            if(lc.get(i).getUsuario().getUsername().equals(usr)==true){
                return lc.get(i);
            }
        }
        return null;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Perfil getPusuaruio() {
        return pusuaruio;
    }

    public void setPusuaruio(Perfil pusuaruio) {
        this.pusuaruio = pusuaruio;
    }

    public Usuario() {
    }

    public Usuario(String username, String password, Perfil pusuaruio) {
        this.username = username;
        this.password = password;
        this.pusuaruio = pusuaruio;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(String username, String password, String nombre, String apellido, Integer edad, Date fechaNac) {
        super(nombre, apellido, edad, fechaNac);
        this.username = username;
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

   

    
    
    
}
