package com.ejemploII.SpringBoot.security.entity;

import com.ejemploII.SpringBoot.security.enums.RolNombre;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Colegio
 * @see "Esta clase es para el token y el tema de la seguridad..."
 */
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated(EnumType.STRING)//Esta anotation define los valores que vamos a ingresar
    private RolNombre rolNombre;

    //Constructor
    public Rol() {//Este se genera por detrás, pero lo pusimos igual
    }

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    //Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

}
