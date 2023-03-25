package com.ejemploII.SpringBoot.repository;

import com.ejemploII.SpringBoot.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Colegio
 * @see "<Perosona,Long> Persona es el objeto que vamos a persistir en la DB y
 * Long es el tipo de dato de ID(primary key)"
 * Esta {@link JpaRepository} tiene todos los métodos que proporciona JPA
 */
@Repository //marcamos que esta interface es nuestro repositorio
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
