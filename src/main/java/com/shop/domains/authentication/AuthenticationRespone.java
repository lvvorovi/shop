package com.shop.domains.authentication;

public class AuthenticationRespone {

    private final String jwt;

    public AuthenticationRespone(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
