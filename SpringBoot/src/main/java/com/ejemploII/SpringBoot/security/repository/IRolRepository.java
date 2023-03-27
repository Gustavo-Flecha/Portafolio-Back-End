package com.ejemploII.SpringBoot.security.repository;

import com.ejemploII.SpringBoot.security.entity.Rol;
import com.ejemploII.SpringBoot.security.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Colegio
 * @version 2.0
 */
@Repository //Marcamos que esta es nuestro repositorio
public interface IRolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
