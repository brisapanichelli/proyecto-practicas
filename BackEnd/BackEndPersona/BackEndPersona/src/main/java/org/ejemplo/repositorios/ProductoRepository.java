package org.ejemplo.repository;

import org.ejemplo.modelos.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombre(String nombre);

    List<Producto> findByPrecio(float precio);

    List<Producto> findByMarca(String marca);

    List<Producto> findByModelo(String modelo);

    @Query("SELECT p FROM Producto p WHERE (:nombre IS NULL OR p.nombre = :nombre) AND " +
            "(:precio IS NULL OR p.precio = :precio) AND " +
            "(:marca IS NULL OR p.marca = :marca) AND " +
            "(:modelo IS NULL OR p.modelo = :modelo)")
    List<Producto> buscarPorParametros(
            @Param("nombre") String nombre,
            @Param("precio") Float precio,
            @Param("marca") String marca,
            @Param("modelo") String modelo);
}
