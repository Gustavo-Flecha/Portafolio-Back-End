package com.ejemploII.SpringBoot.security.controller;

import com.ejemploII.SpringBoot.security.Dto.JwtDto;
import com.ejemploII.SpringBoot.security.Dto.LoginUsuario;
import com.ejemploII.SpringBoot.security.Dto.NuevoUsuario;
import com.ejemploII.SpringBoot.security.entity.Rol;
import com.ejemploII.SpringBoot.security.entity.Usuario;
import com.ejemploII.SpringBoot.security.enums.RolNombre;
import com.ejemploII.SpringBoot.security.jwt.JwtProvider;
import com.ejemploII.SpringBoot.security.service.RolService;
import com.ejemploII.SpringBoot.security.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Colegio
 * @see "Esta clase es la que se va a comunicar con el front-end"
 * @see {@link AuthController AtenticationController}"Se realiza todas las
 * inyecciones para que funcione la autenticación"
 *
 * @version 2.0
 */
@RestController //1:21:00hs'
@RequestMapping("/auth")//Esto es para decirle desde dónde le vamos a llamar
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos o email inválido"),
                    HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("Ese nombre de usuario ya existe"),
                    HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity(new Mensaje("Ese email ya existe"),
                    HttpStatus.BAD_REQUEST);
        }
        /**
         * @see "Creamos un nuevo usuario por completo"
         */
        Usuario usuario = new Usuario(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));

        /*
          Agregamos los roles que por defecto todos van a tener ROLE_USER
          a menos que contenga admin y entonces se le va a asignar el
          ROLE_ADMIN.
         */
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get()); //no funciona bien 
        //por defecto así que le mandé en un if para que me guarde los de ROLE_USER
      

        if (nuevoUsuario.getRoles().contains("admin")) {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            usuario.setRoles(roles);
            usuarioService.save(usuario);
        } else {
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
            usuario.setRoles(roles);
            usuarioService.save(usuario);
        }
        return new ResponseEntity(new Mensaje("Muy bien generaste un nuevo usuario"), HttpStatus.CREATED);
    }

   @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
       
        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginUsuario.getNombreUsuario(),
                        loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt,
                userDetails.getUsername(),
                userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}

