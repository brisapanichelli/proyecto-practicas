package org.ejemplo.validations;

import org.ejemplo.exceptions.ClienteValidationExceptions;
import org.ejemplo.modelos.Cliente;
import org.ejemplo.modelos.Persona;

import java.util.regex.Pattern;

public class ClientesValidation {
    public static void validar(Cliente cliente) throws ClienteValidationExceptions {
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new ClienteValidationExceptions("El nombre es obligatorio");
        }

        if (cliente.getApellido() == null || cliente.getApellido().isBlank()) {
            throw new ClienteValidationExceptions("El apellido es obligatorio");
        }

        if (cliente.getEmail() == null || cliente.getEmail().isBlank() || !esEmailValido(cliente.getEmail())) {
            throw new ClienteValidationExceptions("El email no es válido o está en blanco");
        }

        if (cliente.getDomicilio() == null || cliente.getDomicilio().isBlank()) {
            throw new ClienteValidationExceptions("El domicilio es obligatorio");
        }

        if (cliente.getTipoUsuario() == null || !cliente.getTipoUsuario().equalsIgnoreCase("cliente")) {
            throw new ClienteValidationExceptions("El tipo de usuario debe ser 'cliente'");
        }
    }

    private static boolean esEmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, email);
    }
}
