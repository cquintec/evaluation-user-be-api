package com.bci.evaluation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.evaluation.domain.AuthenticationRQ;
import com.bci.evaluation.domain.AuthenticationRS;
import com.bci.evaluation.service.impl.CustomUserDetailsService;
import com.bci.evaluation.utils.JWTUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthorizationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private JWTUtil jwtUtil;
    
    @GetMapping(value = "/token", 
            produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AuthenticationRS> getAuthorizationToken(@RequestBody AuthenticationRQ authenticationRQ) {
        
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRQ.getUserName(), authenticationRQ.getPassword()));    
        } catch (BadCredentialsException e) {
          log.info(e.getMessage());
        }
        
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRQ.getUserName());
        
        final String jwt=jwtUtil.generateToken(userDetails);
        
        return new ResponseEntity<>(new AuthenticationRS(jwt),HttpStatus.OK);
    }
    
    
}
