    package org.ejemplo.servicios;

    import lombok.extern.slf4j.Slf4j;
    import org.ejemplo.exceptions.UserValidationsExceptions;
    import org.ejemplo.modelos.Login;
    import org.ejemplo.modelos.Persona;
    import org.ejemplo.repositorios.PersonaRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    import static org.ejemplo.validations.UserValidations.validateExistUser;

    @Service
    @Slf4j
    public class PersonaService {

        private final PersonaRepository personaRepository;

        @Autowired
        public PersonaService(PersonaRepository personaRepository) {
            this.personaRepository = personaRepository;
        }

        public String guardarUsuario(Persona usuario) throws UserValidationsExceptions {
            if (personaRepository.existsById(usuario.getId())) {
                return "Error, el usuario ya existe";
            }
            personaRepository.save(usuario);
            return "Usuario cargado correctamente";
        }

        public List<Persona> retornarUsuarios() {
            return personaRepository.findAll();
        }

        public void borrarUsuarios(String user) {
            Optional<Persona> usuarioABorrar = personaRepository.findByNombre(user);
            usuarioABorrar.ifPresentOrElse(
                    usuario -> personaRepository.delete(usuario),
                    () -> log.warn("El usuario {} no existe y no lo podemos borrar", user)
            );
        }

        public String login(Login login) {
            Optional<Persona> usuario = personaRepository.findByEmail(login.getUser());
            if (usuario.isPresent()) {
                if (usuario.get().getPassword().equals(login.getPassword())) {
                    return usuario.get().getTipoUsuario();
                } else {
                    return "Error, contraseña incorrecta";
                }
            }
            return String.format("Error, el usuario %s no existe, por favor regístrese", login.getUser());
        }
    }
