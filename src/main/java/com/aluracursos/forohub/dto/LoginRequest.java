package com.aluracursos.forohub.dto;

public class LoginRequest {
    private String username;
    private String password;

    // Constructor sin argumentos
    public LoginRequest() {
    }

    // Constructor con todos los argumentos
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
