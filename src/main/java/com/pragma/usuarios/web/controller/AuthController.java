package com.pragma.usuarios.web.controller;

import com.pragma.usuarios.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String clave) {
        return authService.authenticate(correo, clave);
    }
}
