package com.ejemploII.SpringBoot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author Colegio
 * @Getter y @Setter de la dependencia lombok; nos permiten automaticamente 
 * incorporar los getter y setter sin escribirlos, lo que nos ahorra trabajo (y no tener un código de metros).
 */
@Getter @Setter
@Entity
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) /*El GenerationType.AUTO es el tipo de generación por defecto y
permite que el proveedor de persistencia (hibernate) elegir la estrategia de generación*/
    private Long id;
    private String nombre;
    private String apellido;
    private String img; //Imagen de portada

    public Persona() {
    }

    public Persona(Long id, String nombre, String apellido,String img) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.img= img;
    }
}