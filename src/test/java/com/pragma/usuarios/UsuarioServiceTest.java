package com.pragma.usuarios;

import com.pragma.usuarios.application.service.UsuarioService;
import com.pragma.usuarios.domain.model.Role;
import com.pragma.usuarios.domain.model.Usuario;
import com.pragma.usuarios.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void testCrearPropietario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        usuario.setApellido("Pérez");
        usuario.setDocumentoDeIdentidad("12345678");
        usuario.setCelular("+573005698325");
        usuario.setFechaNacimiento(LocalDate.of(1990, 1, 1));
        usuario.setCorreo("juan.perez@example.com");
        usuario.setClave("password");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario creado = usuarioService.crearPropietario(usuario);
        assertNotNull(creado);
        assertEquals(Role.PROPIETARIO, creado.getRol());
    }
}
