package com.ejemploII.SpringBoot.controller;

import com.ejemploII.SpringBoot.model.Persona;
import com.ejemploII.SpringBoot.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Colegio
 * @class Controller : va a encargarse de recibir todas las peticiones de los
 * usuarios clientes y va a encargarse de dar las correspondientes respuestas,
 * deberá decidir a dónde dirigir cada una de las operaciones o solisitudes de
 * los clientes.
 */
@RestController // Con esta anotation le decimos a SpringBoot que va a hacer nuestra clase controladora
@CrossOrigin(origins = "http://localhost:4200")//Esto es para que acepte la conexión que va a entrar
public class Controller {

    @Autowired //inyectamos las dependencias

    private IPersonaService persoServ;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new/persona") //2:21:50
    //Con @RequestBody mandamos los datos del front al back
    public void agregarPersona(@RequestBody Persona per) {
        persoServ.crearPersona(per);
    }

    @GetMapping("/ver/personas")
    @ResponseBody
    public List<Persona> verPersona() {
        return persoServ.verPersonas();//En caso de que esté vació nos devolverá un JSON vacio. 
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/modificar/persona/{id}")
    public void modificarPersona(@PathVariable Long id, @RequestBody Persona per) {
        persoServ.modificarPersona(per);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void borarPersona(@PathVariable Long id) {
        persoServ.borrarPersona(id);
    }

    @GetMapping("/traer/perfil/{id}")
    public Persona traerPerfil(@PathVariable Long id) {
        return persoServ.buscarPersona(id);
    }

    /**
     * @return con el método @GetMapping podremos devolver algo al usuario
     * (traemos algo de la base de datos y se lo mostramos)
     */
    @GetMapping("/hola") //Lo dejé para poder probar
    public String decirHola() {
        return "Hola mundo soy Gustavo desde el BackEnd con Java";
    }
}
