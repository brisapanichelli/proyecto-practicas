package org.ejemplo.controladores;

import org.ejemplo.exceptions.UserValidationsExceptions;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Persona;
import org.ejemplo.servicios.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonaController {
    private final PersonaService personaService;
    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/registry")
    public ResponseEntity<String> createUser(@RequestBody Persona persona){
        String respuesta = null;
        try {
            respuesta = personaService.guardarUsuario(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (UserValidationsExceptions e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(respuesta);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Persona>> getAll(){
        List<Persona> personas = personaService.retornarUsuarios();
        return ResponseEntity.ok(personas);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        String respuesta = personaService.login(login);
        if (respuesta.contains("Error")){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(respuesta);
        }
        return ResponseEntity.ok(respuesta);
    }
}