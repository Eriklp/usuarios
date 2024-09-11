package com.pragma.usuarios.application.service;

import com.pragma.usuarios.domain.model.Role;
import com.pragma.usuarios.domain.model.Usuario;
import com.pragma.usuarios.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Validated
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearPropietario(@Valid Usuario usuario) {
        // Verificar que el usuario es mayor de edad
        if (usuario.getFechaNacimiento().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("El usuario debe ser mayor de edad.");
        }
        // Establecer rol
        usuario.setRol(Role.PROPIETARIO);
        // Guardar el usuario
        return usuarioRepository.save(usuario);
    }
}

