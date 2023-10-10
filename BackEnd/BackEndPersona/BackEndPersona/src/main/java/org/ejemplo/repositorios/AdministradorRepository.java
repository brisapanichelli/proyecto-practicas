package org.ejemplo.repositorios;

import org.ejemplo.modelos.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByEmailAndPassword(String email, String contraseña);
}
