package com.ejemploII.SpringBoot.security.repository;

import com.ejemploII.SpringBoot.security.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Colegio
 * @version 2.0
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    /**
     * @param nombreUsuario los booleanos nos devolverán si existe o no un
     * Usuario
     * @param email como así también si existe el email
     */
    boolean existsByNombreUsuario(String nombreUsuario);

    boolean existsByEmail(String email);
}
