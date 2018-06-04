/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arg.edu.iua.modelo1;

import java.util.Date;

/**
 *
 * @author curso
 */
public class Perfil {
    String nombre;
    String apellido;
    Integer edad;
    Date fechaNac;
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Perfil() {
    }

    public Perfil(String nombre, String apellido, Integer edad, Date fechaNac) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaNac = fechaNac;
    }
    
    
}
