package com.pragma.usuarios.web.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "El correo es obligatorio")
    private String correo;  // Cambiado a 'correo'

    @NotBlank(message = "La contraseña es obligatoria")
    private String clave;  // Cambiado a 'clave'

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
