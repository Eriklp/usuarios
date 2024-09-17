package com.pragma.usuarios.web.controller;

import com.pragma.usuarios.application.service.UsuarioService;
import com.pragma.usuarios.domain.model.Role;
import com.pragma.usuarios.domain.model.Usuario;
import com.pragma.usuarios.web.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Optional;

@RestController
@RequestMapping("/")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Crear propietario: Solo un usuario con rol "ADMIN" puede crear un propietario.
     */
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/propietario")
    public ResponseEntity<Usuario> crearPropietario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoPropietario = usuarioService.crearPropietario(usuario);
        return new ResponseEntity<>(nuevoPropietario, HttpStatus.CREATED);
    }

    /**
     * Crear empleado: Solo un usuario con rol "PROPIETARIO" puede crear un empleado.
     */
    @PreAuthorize("hasRole('PROPIETARIO')")
    @PostMapping("/empleado")
    public ResponseEntity<Usuario> crearEmpleado(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoEmpleado = usuarioService.crearEmpleado(usuario);
        return new ResponseEntity<>(nuevoEmpleado, HttpStatus.CREATED);
    }

    /**
     * Obtener un usuario por ID: Solo usuarios autenticados pueden acceder a este endpoint.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getRol().toString());
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint sin seguridad para crear el primer administrador
    @PostMapping("/admin/crear")
    public ResponseEntity<Usuario> crearAdmin(@Valid @RequestBody Usuario usuario) {
        // Convertir el rol a enum Role
        usuario.setRol(Role.ADMINISTRADOR); // Establecer el rol como ADMINISTRADOR
        Usuario nuevoAdmin = usuarioService.crearAdministrador(usuario);
        return new ResponseEntity<>(nuevoAdmin, HttpStatus.CREATED);
    }

}
