package org.ejemplo.servicios;

import org.ejemplo.modelos.Producto;
import org.ejemplo.exceptions.ProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    private List<Producto> productos = new ArrayList<>();
    private final ProductoValidation productoValidation;

    @Autowired
    public ProductoService(ProductoValidation productoValidation) {
        this.productoValidation = productoValidation;
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productos;
    }

    public Producto obtenerProductoPorId(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        throw new ProductException("No se encontró el producto con el ID dado.");
    }

    public Producto agregarProducto(Producto producto) {
        if (productoValidation.validarProducto(producto)) {
            producto.setId(generarIdUnico());
            productos.add(producto);
            return producto;
        } else {
            throw new ProductException("El producto no pasa las validaciones.");
        }
    }

    public Producto actualizarProducto(int id, Producto productoActualizado) {
        Producto productoExistente = obtenerProductoPorId(id);
        if (productoValidation.validarProducto(productoActualizado)) {
            productoExistente.setNombre(productoActualizado.getNombre());
            productoExistente.setDescripcion(productoActualizado.getDescripcion());
            productoExistente.setPrecio(productoActualizado.getPrecio());
            productoExistente.setMarca(productoActualizado.getMarca());
            productoExistente.setModelo(productoActualizado.getModelo());
            return productoExistente;
        } else {
            throw new ProductException("El producto actualizado no pasa las validaciones.");
        }
    }

    public void eliminarProducto(int id) {
        Producto producto = obtenerProductoPorId(id);
        productos.remove(producto);
    }

    private int generarIdUnico() {
        return productos.size() + 1;
    }
}
