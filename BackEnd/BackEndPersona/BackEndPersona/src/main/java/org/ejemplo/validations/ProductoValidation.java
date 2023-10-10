package org.ejemplo.validations;

import org.ejemplo.exceptions.ProductoValidationException;
import org.ejemplo.modelos.Producto;

public class ProductoValidation {

    public static void validarProducto(Producto producto) {
        if (producto.getPrecio() < 0) {
            throw new ProductoValidationException("El precio no puede ser menor que 0.");
        }
        if (producto.getModelo() == null || producto.getModelo().trim().isEmpty()) {
            throw new ProductoValidationException("El modelo no puede estar en blanco.");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) {
            throw new ProductoValidationException("La descripción no puede estar en blanco.");
        }
        if (producto.getMarca() == null || producto.getMarca().trim().isEmpty()) {
            throw new ProductoValidationException("La marca no puede estar en blanco.");
        }
    }
}
