package com.ejemploII.SpringBoot.security.controller;

/**
 * @author Colegio
 */
public class Mensaje {
    private String mensaje;
    
    //Agregamos los constructores

    public Mensaje() {
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    //Generamos los Getters y Setters

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
