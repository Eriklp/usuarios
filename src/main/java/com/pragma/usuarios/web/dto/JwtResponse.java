package com.pragma.usuarios.web.dto;

public class JwtResponse {

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
