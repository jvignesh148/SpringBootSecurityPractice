package com.example.demo.controller;


import com.example.demo.entity.JWTRequest;
import com.example.demo.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JWTController {

    private static final Logger logger = LoggerFactory.getLogger(JWTController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtil jwtUtil;



    @PostMapping("/authenticate")
    public String generateToken(@RequestBody JWTRequest jwtRequest) {
        logger.info("Received authentication request for user: {}", jwtRequest.getUserName());
        if (jwtRequest.getUserName() == null || jwtRequest.getPassword() == null) {
            logger.error("Invalid request: userName or password is null");
            throw new RuntimeException("Invalid request: userName or password is null");
        }
        try {
            logger.info("Attempting authentication for user: {}", jwtRequest.getUserName());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword())
            );
            logger.info("Authentication successful for user: {}", jwtRequest.getUserName());
            String token = jwtUtil.generateToken(jwtRequest.getUserName());
            logger.info("Generated token for user {}: {}", jwtRequest.getUserName(), token);
            return token;
        } catch (RuntimeException e) {
            logger.error("Authentication failed for user: {}", jwtRequest.getUserName(), e);
            throw new RuntimeException("Invalid username or password", e);
        } catch (Exception e) {
            logger.error("Error generating token for user: {}", jwtRequest.getUserName(), e);
            throw new RuntimeException("Error occurred: " + e.getMessage(), e);
        }   
    }
}
