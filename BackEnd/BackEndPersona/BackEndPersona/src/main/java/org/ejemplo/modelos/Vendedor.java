package org.ejemplo.modelos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vendedores")
@Getter
@Setter
public class Vendedor extends Persona {

    @Column(name = "empresa")
    private String empresa;

    public Vendedor() {
    }

    public Vendedor(String nombre, String apellido, String tipoUsuario, String domicilio, String telefono, String email, String password, String empresa) {
        super(nombre, apellido, tipoUsuario, domicilio, telefono, email, password);
        this.empresa = empresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
