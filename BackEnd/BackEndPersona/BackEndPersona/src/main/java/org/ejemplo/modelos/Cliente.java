package org.ejemplo.modelos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente extends Persona {

    @Column(name = "discapacidad")
    private String discapacidad;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String tipoUsuario, String domicilio, String telefono, String email, String password, String discapacidad) {
        super(nombre, apellido, tipoUsuario, domicilio, telefono, email, password);
        this.discapacidad = discapacidad;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }
}
