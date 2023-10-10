package org.ejemplo.validations;

import org.ejemplo.exceptions.UserValidationsExceptions;
import org.ejemplo.modelos.Persona;

import java.util.List;

public class UserValidations {

    public static void validateExistUser(List<Persona> usuarios, Integer id) throws UserValidationsExceptions {
        for (Persona user : usuarios) {
            if (user.getId() == id) {
                throw new UserValidationsExceptions("El usuario con ID " + id + " ya existe");
            }
        }
    }

    public static void validateUserToSave(List<Persona> usuarios, Persona persona) throws UserValidationsExceptions {
        validateExistUser(usuarios, persona.getId());

        if (persona.getNombre() == null || persona.getNombre().isBlank()) {
            throw new UserValidationsExceptions("El nombre es obligatorio");
        }

        if (persona.getApellido() == null || persona.getApellido().isBlank()) {
            throw new UserValidationsExceptions("El apellido es obligatorio");
        }

        if (persona.getEmail() == null || persona.getEmail().isBlank() || !isValidEmail(persona.getEmail())) {
            throw new UserValidationsExceptions("El email no es válido o está en blanco");
        }

        if (persona.getTelefono() == null || persona.getTelefono().isBlank()) {
            throw new UserValidationsExceptions("El teléfono es obligatorio");
        }

        if (persona.getDomicilio() == null || persona.getDomicilio().isBlank()) {
            throw new UserValidationsExceptions("El domicilio es obligatorio");
        }
    }

    // Método para validar 1 el formato del correo electrónico
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex);
    }
}
