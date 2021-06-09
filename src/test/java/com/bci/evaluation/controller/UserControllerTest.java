package com.bci.evaluation.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bci.evaluation.domain.PhonesDto;
import com.bci.evaluation.domain.UserRequestDto;
import com.bci.evaluation.domain.UserResponseDto;
import com.bci.evaluation.service.UserProcessService;

public class UserControllerTest {

    private static final String TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1biIsImV4cCI6MTYyMzI5MDc1MCwiaWF0IjoxNjIzMjU0NzUwfQ.-LCPXqKn452sGTmiiBjTklSor_rLrfobZdilxFg-aiY";
    
    @InjectMocks
    private UserController userController;
    
    @Mock
    private UserProcessService userProcessService;
    
    @Mock
    private HttpServletRequest request;
    
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }
 
    @DisplayName("Test Mock UserController")
    @Test
    void processUserTest(){
        
        when(request.getHeader("Authorization")).thenReturn(TOKEN);
        when(userProcessService.userPersistanceProcess(dummyUserRequest(),TOKEN)).thenReturn(dummyUserResponse());
        
        ResponseEntity<UserResponseDto> response=userController.processUser(Mockito.any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private UserRequestDto dummyUserRequest() {
        
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setEmail("juan@rodriguez.org");
        userRequestDto.setName("Juan Rodriguez");
        userRequestDto.setPassword("somePassword");
        
        PhonesDto phonesDto=new PhonesDto();
        phonesDto.setCitycode("45");
        phonesDto.setContrycode("52");
        phonesDto.setNumber("12345678");
        List<PhonesDto> phonesDtos=new ArrayList<>();
        phonesDtos.add(phonesDto);
        return userRequestDto;
    }

    private UserResponseDto dummyUserResponse() {
        
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setId("42fd3c4b-d92b-4784-bd8f-d0256d2cc698");
        userResponseDto.setToken(TOKEN);
        userResponseDto.setCreated(new Date());
        userResponseDto.setLastLogin(new Date());
        userResponseDto.setModified(new Date());
        userResponseDto.setActive(true);
        return userResponseDto;
        
    }
    
}
