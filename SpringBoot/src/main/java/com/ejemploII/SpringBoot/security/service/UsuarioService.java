package com.ejemploII.SpringBoot.security.service;

import com.ejemploII.SpringBoot.security.entity.Usuario;
import com.ejemploII.SpringBoot.security.repository.IUsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Colegio
 */
@Service
@Transactional
public class UsuarioService {

    @Autowired
    IUsuarioRepository iusuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nombreUsuario) {
        return iusuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    /**
     * @param nombreUsuario recibe un usuario
     * @return va a verificar si exite un usuario
     */
    public boolean exitsByNombreUsuario (String nombreUsuario){
        return iusuarioRepository.existsByNombreUsuario(nombreUsuario);  
    }
    /**
     * @param email recibe un email 
     * @return va a verificar si exite un email realmente
     */
    public boolean exitsEmail (String email){
        return iusuarioRepository.existsByEmail(email);
    }
    
    /**
     * @param usuario nos va a guardar un usario nuevo
     */
    public void save (Usuario usuario){
        iusuarioRepository.save(usuario);
    }

}
