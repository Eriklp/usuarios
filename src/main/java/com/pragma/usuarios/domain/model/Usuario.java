package com.pragma.usuarios.domain.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {

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

    @NotBlank
    private String clave;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Role rol;

    // Getters and Setters

    public void setClave(String clave) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.clave = encoder.encode(clave);
    }
}

