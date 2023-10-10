package org.ejemplo.controladores;

import org.ejemplo.exceptions.ProductoValidationException;
import org.ejemplo.modelos.Producto;
import org.ejemplo.servicios.ProductoService;
import org.ejemplo.validations.ProductoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private final ProductoService productoService;

    @Autowired
    public ProductosController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable int id) {
        try {
            Producto producto = productoService.obtenerProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarProducto(@RequestBody Producto producto) {
        try {
            ProductoValidation.validarProducto(producto); // Validación en el paquete de validaciones
            Producto nuevoProducto = productoService.agregarProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (ProductoValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int id, @RequestBody Producto producto) {
        try {
            ProductoValidation.validarProducto(producto); // Validación en el paquete de validaciones
            Producto productoActualizado = productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        } catch (ProductoValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    @GetMapping("/buscar")
    public List<Producto> buscarProductosPorParametros(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Float precio,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String modelo) {
        return productoService.buscarProductosPorParametros(nombre, precio, marca, modelo);
    }
}
