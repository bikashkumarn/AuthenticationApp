package com.spring.security.entity;

/**
 * Created by apple on 11/06/17.
 */
public class AuthenticationResponseDTO {

    private final String token;

    public AuthenticationResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
