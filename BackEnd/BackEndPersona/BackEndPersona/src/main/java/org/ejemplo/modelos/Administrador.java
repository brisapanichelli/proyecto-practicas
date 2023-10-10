package org.ejemplo.modelos;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "administradores")
@Getter
@Setter
public class Administrador extends Persona {

    public Administrador() {
    }

    public Administrador(String nombre, String apellido, String tipoUsuario, String domicilio, String telefono, String email, String password) {
        super(nombre, apellido, tipoUsuario, domicilio, telefono, email, password);
    }
}
