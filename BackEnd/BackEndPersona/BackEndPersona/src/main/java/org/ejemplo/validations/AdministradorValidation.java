package org.ejemplo.validations;
import org.ejemplo.exceptions.AdministradorValidationsExceptions;
import org.ejemplo.modelos.Persona;
import org.ejemplo.modelos.Administrador;
import java.util.List;

import java.util.regex.Pattern;

public class AdministradorValidation {

    public static void validar(Administrador administrador) throws AdministradorValidationsExceptions {
        if (administrador.getNombre() == null || administrador.getNombre().isBlank()) {
            throw new AdministradorValidationsExceptions("El nombre no puede estar en blanco");
        }

        if (administrador.getApellido() == null || administrador.getApellido().isBlank()) {
            throw new AdministradorValidationsExceptions("El apellido no puede estar en blanco");
        }

        if (!esEmailValido(administrador.getEmail())) {
            throw new AdministradorValidationsExceptions("El email no es válido");
        }

        if (administrador.getTipoUsuario() == null || !administrador.getTipoUsuario().equalsIgnoreCase("administrador")) {
            throw new AdministradorValidationsExceptions("El tipo de usuario debe ser 'administrador'");
        }
    }


    private static boolean esEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, email);
    }
}
