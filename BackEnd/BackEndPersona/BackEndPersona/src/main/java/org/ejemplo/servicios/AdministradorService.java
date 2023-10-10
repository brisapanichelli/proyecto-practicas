package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.AdministradorValidationsExceptions;
import org.ejemplo.modelos.Administrador;
import org.ejemplo.repositorios.AdministradorRepository;
import org.ejemplo.validations.AdministradorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public String guardarAdministrador(Administrador administrador) throws AdministradorValidationsExceptions {
        AdministradorValidation.validar(administrador);

        if (administradorRepository.existsById(administrador.getId())) {
            return "Error: Administrador ya existe con ese ID";
        }

        administradorRepository.save(administrador);
        return "Administrador registrado exitosamente";
    }

    public List<Administrador> obtenerTodos() {
        return administradorRepository.findAll();
    }

    public String login(String email, String contraseña) {
        Optional<Administrador> administrador = administradorRepository.findByEmailAndPassword(email, contraseña);

        if (administrador.isPresent()) {
            return "Inicio de sesión exitoso";
        } else {
            return "Error: Credenciales incorrectas";
        }
    }

    public String eliminarAdministrador(int id) {
        if (!administradorRepository.existsById(id)) {
            return "Error: Administrador no encontrado con ese ID";
        }

        administradorRepository.deleteById(id);
        return "Administrador eliminado exitosamente";
    }
}
