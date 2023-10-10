package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.VendedorValidationsExceptions;
import org.ejemplo.modelos.Vendedor;
import org.ejemplo.repositorios.VendedorRepository;
import org.ejemplo.validations.VendedorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    @Autowired
    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public String guardarVendedor(Vendedor vendedor) throws VendedorValidationsExceptions {
        VendedorValidation.validar(vendedor);

        if (vendedorRepository.existsById(vendedor.getId())) {
            return "Error: Vendedor ya existe con ese ID";
        }

        vendedorRepository.save(vendedor);
        return "Vendedor registrado exitosamente";
    }

    public List<Vendedor> obtenerTodos() {
        return vendedorRepository.findAll();
    }

    public String login(String email, String contraseña) {
        Optional<Vendedor> vendedor = vendedorRepository.findByEmailAndPassword(email, contraseña);

        if (vendedor.isPresent()) {
            return "Inicio de sesión exitoso";
        } else {
            return "Error: Credenciales incorrectas";
        }
    }

    public String eliminarVendedor(int id) {
        if (!vendedorRepository.existsById(id)) {
            return "Error: Vendedor no encontrado con ese ID";
        }

        vendedorRepository.deleteById(id);
        return "Vendedor eliminado exitosamente";
    }
}
