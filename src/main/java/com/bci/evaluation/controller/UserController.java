package com.bci.evaluation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.evaluation.domain.UserRequestDto;
import com.bci.evaluation.domain.UserResponseDto;
import com.bci.evaluation.service.UserProcessService;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserProcessService userProcessService;
    
    @Autowired
    private HttpServletRequest request;
    
    @PostMapping(value = "/user", 
            produces = MediaType.APPLICATION_JSON_VALUE, 
            consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<UserResponseDto> processUser(@Valid @RequestBody UserRequestDto userRequest){
        
        final String authorizationHeader=request.getHeader("Authorization");
        
        UserResponseDto response = userProcessService.userPersistanceProcess(userRequest,authorizationHeader);
        
        return new ResponseEntity<>(response,HttpStatus.OK);
        
    }
    
}
