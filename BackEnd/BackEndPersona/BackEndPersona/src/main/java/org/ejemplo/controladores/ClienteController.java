package org.ejemplo.controladores;
import org.ejemplo.exceptions.ClienteValidationExceptions;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Cliente;
import org.ejemplo.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/nuevo") //http://localhost:8081/clientes/nuevo
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        String respuesta = null;
        try {
            respuesta = clienteService.guardarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (ClienteValidationExceptions e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = clienteService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

}
