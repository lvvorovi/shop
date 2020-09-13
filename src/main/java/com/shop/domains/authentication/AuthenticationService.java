package com.shop.domains.authentication;

import com.shop.core.security.MyUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, MyUserDetailService userDetailService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationRespone authenticate(AuthenticationRequest request) throws BadCredentialsException {
        logger.info("authentication method entered");
        try {
            logger.info("authentication started");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            logger.info("authentication ended");
        } catch (Exception e) {
            logger.error("authentication failed");
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        logger.info("authentication passed, creating token");
        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        logger.info("token created");
        return new AuthenticationRespone(jwt);

    }


}
