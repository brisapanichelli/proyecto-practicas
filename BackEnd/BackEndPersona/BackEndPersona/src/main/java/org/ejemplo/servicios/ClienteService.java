package org.ejemplo.servicios;

import lombok.extern.slf4j.Slf4j;
import org.ejemplo.exceptions.ClienteValidationExceptions;
import org.ejemplo.modelos.Cliente;
import org.ejemplo.repositorios.ClienteRepository;
import org.ejemplo.validations.ClientesValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public String guardarCliente(Cliente cliente) throws ClienteValidationExceptions {
        ClientesValidation.validar(cliente);

        if (clienteRepository.existsById(cliente.getId())) {
            return "Error: Cliente ya existe con ese ID";
        }

        clienteRepository.save(cliente);
        return "Cliente registrado exitosamente";
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public String login(String email, String contraseña) {
        Optional<Cliente> cliente = clienteRepository.findByEmailAndPassword(email, contraseña);

        if (cliente.isPresent()) {
            return "Inicio de sesión exitoso";
        } else {
            return "Error: Credenciales incorrectas";
        }
    }

    public String eliminarCliente(int id) {
        if (!clienteRepository.existsById(id)) {
            return "Error: Cliente no encontrado con ese ID";
        }

        clienteRepository.deleteById(id);
        return "Cliente eliminado exitosamente";
    }
}
