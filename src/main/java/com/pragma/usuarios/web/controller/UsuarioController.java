package com.pragma.usuarios.web.controller;

import com.pragma.usuarios.application.service.UsuarioService;
import com.pragma.usuarios.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/propietario")
    public ResponseEntity<Usuario> crearPropietario(@Valid @RequestBody Usuario usuario) {
        Usuario nuevoPropietario = usuarioService.crearPropietario(usuario);
        return new ResponseEntity<>(nuevoPropietario, HttpStatus.CREATED);
    }
}

