package org.ejemplo.controladores;
import org.ejemplo.exceptions.AdministradorValidationsExceptions;
import org.ejemplo.modelos.Administrador;
import org.ejemplo.servicios.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    private final AdministradorService administradorService;
    @Autowired
    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @PostMapping("/nuevo") // POST http://localhost:8081/administradores/nuevo
    public ResponseEntity<String> crearAdministrador(@RequestBody Administrador administrador) {
        /*La ya que estamos manejando excepciones la mejor forma de entender si fallamos es con un try/catch
        String respuesta = administradorService.guardarAdministrador(administrador);
        if (respuesta.contains("error")) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(respuesta);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);*/

        try {
            String respuesta = administradorService.guardarAdministrador(administrador);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (AdministradorValidationsExceptions e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @GetMapping("/todos")// GET  http://localhost:8081/administradores/todos
    public ResponseEntity<List<Administrador>> obtenerAdministradores() {
        List<Administrador> administradores = administradorService.obtenerTodos();
        return ResponseEntity.ok(administradores);
    }

}
