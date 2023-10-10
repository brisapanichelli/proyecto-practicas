package org.ejemplo.controladores;
import org.ejemplo.exceptions.VendedorValidationsExceptions;
import org.ejemplo.modelos.Login;
import org.ejemplo.modelos.Vendedor;
import org.ejemplo.servicios.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;
    @Autowired
    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping("/nuevo")
    public ResponseEntity<String> crearVendedor(@RequestBody Vendedor vendedor) {
        String respuesta = null;
        try {
            respuesta = vendedorService.guardarVendedor(vendedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } catch (VendedorValidationsExceptions e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Vendedor>> obtenerVendedores() {
        List<Vendedor> vendedores = vendedorService.obtenerTodos();
        return ResponseEntity.ok(vendedores);
    }

}
