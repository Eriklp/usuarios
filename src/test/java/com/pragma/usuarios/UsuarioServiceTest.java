package com.pragma.usuarios;

import com.pragma.usuarios.application.service.UsuarioService;
import com.pragma.usuarios.domain.model.Usuario;
import com.pragma.usuarios.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;  // Mockear el PasswordEncoder

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearPropietario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setDocumentoDeIdentidad("12345678");
        usuario.setCelular("+573005698325");
        usuario.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        usuario.setCorreo("juan.perez@example.com");
        usuario.setClave("password");  // Asegúrate de establecer la clave aquí

        // Simular el comportamiento del passwordEncoder
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Simular el guardado del usuario
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Ejecutar la prueba
        Usuario creado = usuarioService.crearPropietario(usuario);

        // Verificar que el usuario fue creado con la clave encriptada
        assertNotNull(creado);
        assertEquals("encodedPassword", creado.getClave());
    }
}

