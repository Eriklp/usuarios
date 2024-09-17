package com.pragma.usuarios.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotBlank
    @Size(max = 50)
    private String nombre;

    @Getter
    @Setter
    @NotBlank
    @Size(max = 50)
    private String apellido;

    @Getter
    @Setter
    @NotBlank
    @Pattern(regexp = "\\d+")
    private String documentoDeIdentidad;

    @Getter
    @Setter
    @NotBlank
    @Size(max = 13)
    @Pattern(regexp = "^\\+?\\d+$")
    private String celular;

    @Getter
    @Setter
    @Past
    private LocalDate fechaNacimiento;

    @Getter
    @Setter
    @NotBlank
    @Email
    private String correo;

    @Getter
    @Setter
    @NotBlank
    private String clave;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Role rol;

    // Métodos de la interfaz UserDetails que debes implementar
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.rol.name()));
    }

    @Override
    public String getPassword() {
        return this.clave;  // Este es el campo de clave encriptada
    }

    @Override
    public String getUsername() {
        return this.correo;  // Este será el username (puedes usar el correo)
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Puedes ajustar esto según tus reglas de negocio
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Puedes ajustar esto según tus reglas de negocio
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Puedes ajustar esto según tus reglas de negocio
    }

    @Override
    public boolean isEnabled() {
        return true;  // Puedes ajustar esto según tus reglas de negocio
    }
}
