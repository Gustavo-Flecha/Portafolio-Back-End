package com.ejemploII.SpringBoot.service;

import com.ejemploII.SpringBoot.model.Persona;
import com.ejemploII.SpringBoot.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Colegio {@link @Service} Marcamos que esta es nuestra capa de
 * servicio
 */
@Service
/**
 * Esta clase {@link PersonaService} tendrá la implementación de todos lo
 * métodos que vamos a declararemos en la IPersonaService.
 */
public class PersonaService implements IPersonaService {

    /**
     * @see {@link Autowired] "(es una anotation dentro de SpringBoot) que nos permite hacer
     * inyección de dependencia entre una clase con otra sin la necesidad de
     * estar creando un montón de instancias"
     */
    @Autowired
    public PersonaRepository persoRepo;

    /**
     * Con {@link PersonaRepository} creamos una "instancia" para Autowired,
     * esto nos permitirá implementar cada uno de los métodos de JPA que el
     * repositorio nos otorga.
     */
    @Override
    public List<Persona> verPersonas() {
        return persoRepo.findAll();  //findAll el método que se utiliza para traer todos los elementos 
    }

    @Override
    public void crearPersona(Persona per) {
        persoRepo.save(per); //save guarda la persona que queremos guardar
    }
    
    
    @Override
    public void borrarPersona(Long id) {
        persoRepo.deleteById(id); //Borrar una persona mediante el id
    }

    /**
     * @param id con el findById buscamos la perosona, si no la encontramos
     * orElse (o sino) nos tira null
     */
    @Override
    public Persona buscarPersona(Long id) {
        return persoRepo.findById(id).orElse(null); //Buscar una persona a partir del id

    }

    @Override
    public void modificarPersona(Persona per) {
       persoRepo.save(per);
       
    }   
}