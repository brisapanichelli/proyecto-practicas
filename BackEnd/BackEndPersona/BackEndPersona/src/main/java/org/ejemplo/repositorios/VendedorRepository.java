package org.ejemplo.repositorios;

import org.ejemplo.modelos.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    Optional<Vendedor> findByEmailAndPassword(String email, String contraseña);
}
