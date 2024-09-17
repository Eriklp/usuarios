package com.pragma.usuarios.application.service;

import com.pragma.usuarios.config.JwtTokenProvider;
import com.pragma.usuarios.domain.model.Usuario;
import com.pragma.usuarios.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
public class AuthService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String authenticate(String correo, String clave) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(correo, clave)
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(correo);
        return jwtTokenProvider.generateTokenFromUsuario(userDetails);
    }
}
