package com.shop.controllers;

import com.shop.domains.authentication.AuthenticationRequest;
import com.shop.domains.authentication.AuthenticationRespone;
import com.shop.domains.authentication.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationRespone> createAuthenticationToken
            (@RequestBody AuthenticationRequest authenticationRequest) {
        logger.info("Authentification controller entered");
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

}
