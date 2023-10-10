package org.ejemplo.servicios;

import org.ejemplo.modelos.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    public List<Producto> obtenerTodosLosProductos();

    public Producto obtenerProductoPorId(int id);

    public Producto agregarProducto(Producto producto);

    public Producto actualizarProducto(int id, Producto producto);

    void eliminarProducto(int id);

    public List<Producto> buscarProductosPorParametros(String nombre, Float precio, String marca, String modelo);
}
