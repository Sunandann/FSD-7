package com.example.experiment7.dto;

import java.util.List;

public class LoginResponse {
    private String message;
    private String username;
    private List<String> roles;
    private String basicAuthToken;

    public LoginResponse() {
    }

    public LoginResponse(String message, String username, List<String> roles, String basicAuthToken) {
        this.message = message;
        this.username = username;
        this.roles = roles;
        this.basicAuthToken = basicAuthToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getBasicAuthToken() {
        return basicAuthToken;
    }

    public void setBasicAuthToken(String basicAuthToken) {
        this.basicAuthToken = basicAuthToken;
    }
}
