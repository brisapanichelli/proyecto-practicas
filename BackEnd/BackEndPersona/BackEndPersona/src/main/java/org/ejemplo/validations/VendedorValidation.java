package org.ejemplo.validations;
import org.ejemplo.exceptions.VendedorValidationsExceptions;
import org.ejemplo.modelos.Persona;
import org.ejemplo.modelos.Vendedor;
import java.util.List;
import java.util.regex.Pattern;

public class VendedorValidation {

        public static void validar(Vendedor vendedor) throws VendedorValidationsExceptions {
            if (vendedor.getNombre() == null || vendedor.getNombre().isBlank()) {
                throw new VendedorValidationsExceptions("El nombre es obligatorio");
            }

            if (vendedor.getApellido() == null || vendedor.getApellido().isBlank()) {
                throw new VendedorValidationsExceptions("El apellido es obligatorio");
            }

            if (vendedor.getEmail() == null || vendedor.getEmail().isBlank()) {
                throw new VendedorValidationsExceptions("El email es obligatorio");
            }

            if (!esEmailValido(vendedor.getEmail())) {
                throw new VendedorValidationsExceptions("El email no es válido");
            }

            if (vendedor.getTipoUsuario() == null || !vendedor.getTipoUsuario().equalsIgnoreCase("vendedor")) {
                throw new VendedorValidationsExceptions("El tipo de usuario debe ser 'vendedor'");
            }

            if (vendedor.getEmpresa() == null || vendedor.getEmpresa().isBlank()) {
                throw new VendedorValidationsExceptions("El campo empresa es obligatorio");
            }

        }

        private static boolean esEmailValido(String email) {
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            return Pattern.matches(regex, email);
        }
    }

