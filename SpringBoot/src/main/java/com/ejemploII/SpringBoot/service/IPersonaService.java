/**
 *
 * @author Colegio
 * @see "Esta capa de servicio se encarga de la lógica de negocio, las
 * funciones, de qué maneras traerá los datos que necesitamos cómo los manejará,
 * cómo los presentará la usuario, cómo busca, es decir, todo el procesamiento
 * lógico que hay estará en esta capa."
 *
 */
package com.ejemploII.SpringBoot.service;

import com.ejemploII.SpringBoot.model.Persona;
import java.util.List;

public interface IPersonaService {

    /**
     * Usar {@link String#List<Persona> verPersonas} para traer una lista de
     * personas
     */
    public List<Persona> verPersonas();

    /**
     * Usar {@link String#crearPersona crearPersonas} para crear una persona
     */
    public void crearPersona(Persona per);

    /**
     * Usar {@link String#borrarPersona borrarPersonas} para borrar una persona
     * por id
     */
    public void borrarPersona(Long id);

    /**
     * Usar {@link String#buscarPersona buscarPersonas} para buscar una persona
     * por id
     */
    public Persona buscarPersona(Long id);
    
    
    public void modificarPersona(Persona per);
}
