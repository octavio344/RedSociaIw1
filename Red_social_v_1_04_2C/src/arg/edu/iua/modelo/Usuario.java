/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.modelo;

import arg.edu.iua.modelo.exception.NoDataFoundException;
import arg.edu.iua.modelo.util.ErrorDeSintaxis;
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

    public Usuario(String username, String password, String nombre, String apellido, Integer edad, Date fechaNac,String email) {
        super(nombre, apellido, edad, fechaNac,email);
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
        return true;
    }

  
    public ErrorDeSintaxis chequeaCampos(String cpass) throws NoDataFoundException{
        if(getNombre()==null||getNombre().trim().length()<3){
            return new ErrorDeSintaxis("nombre","El campo nombre es obligatorio");
        }
        if(getApellido()==null||getApellido().trim().length()<3){
            return new ErrorDeSintaxis("apellido","El campo apellido es obligatorio");
        }
        if(getUsername()==null||getUsername().trim().length()<4){
            return new ErrorDeSintaxis("username","El campo username debe contener al menos 4 caracteres");
        }
        if(getPassword()==null||getPassword().trim().length()<6){
            return new ErrorDeSintaxis("password", "La contraseña debe tener al menos 6 caracteres");
        }
        if(cpass==null||getPassword().trim().length()<6){
            return new ErrorDeSintaxis("Conf password","El campo reingresar contraseña es obligatorio");
        }
        if(getEmail()==null||getEmail().trim().length()<2){
            return new ErrorDeSintaxis("Email","El campo email es obligatorio");
        }
        else{
            return null;
        }
        
        
        
        
    }

   public String cheuqueaEdad()throws NoDataFoundException{
            String str=null;
            if(getEdad()<16){
                return str="Para registrarse al menos debe tener 16 años";
            }
            else{
                return str;
            }
        }

    
    
    
}
