package org.ejemplo.repositorios;

import org.ejemplo.modelos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByEmail(String email);
    Optional<Persona> findByNombre(String nombre);
}
