package com.pragma.usuarios.application.service;

import com.pragma.usuarios.domain.model.Role;
import com.pragma.usuarios.domain.model.Usuario;
import com.pragma.usuarios.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearPropietario(@Valid Usuario usuario) {
        if (usuario.getFechaNacimiento().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("El usuario debe ser mayor de edad.");
        }
        usuario.setRol(Role.PROPIETARIO);
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }

    public Usuario crearEmpleado(@Valid Usuario usuario) {
        usuario.setRol(Role.EMPLEADO);
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }

    public Usuario crearAdministrador(@Valid Usuario usuario) {
        usuario.setRol(Role.ADMINISTRADOR);
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public boolean validarRol(Long id, Role rol) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return usuario.getRol().equals(rol);
    }
}
