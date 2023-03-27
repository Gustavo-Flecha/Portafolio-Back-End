package com.ejemploII.SpringBoot.security.service;

import com.ejemploII.SpringBoot.security.entity.Rol;
import com.ejemploII.SpringBoot.security.enums.RolNombre;
import com.ejemploII.SpringBoot.security.repository.IRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Colegio
 * @version 2.0
 * @see "Aquí usamos la anotation
 * @link {@Transactional} para que cuando una operación falla esta anotation hace que
 * se mantenga en un estado anterior de modo que no impacte en la base de datos
 * y se nos complique todo"
 * 
 */
@Service //Marcamos que esta clase es de servicio
@Transactional
public class RolService {
    @Autowired
    IRolRepository irolRepository;
    
     //Creamos un objeto de tipo RolNombre
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    }
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
