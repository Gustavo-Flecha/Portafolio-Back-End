
package com.ejemploII.SpringBoot.security.Dto;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Colegio
 * @see "Esta clase va a ser para nuetro login"
 */
public class LoginUsuario {
    @NotBlank //Esto es para impedir que estén vacíos
    private String nombreUsuario;
    @NotBlank
    private String password;
    
    //Generamos los getters y setters

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}
