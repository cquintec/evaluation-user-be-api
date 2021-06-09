package com.bci.evaluation.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bci.evaluation.domain.PhonesDto;
import com.bci.evaluation.domain.UserRequestDto;
import com.bci.evaluation.domain.UserResponseDto;
import com.bci.evaluation.entities.Phone;
import com.bci.evaluation.entities.User;
import com.bci.evaluation.exception.UserException;
import com.bci.evaluation.repository.UserRepository;
import com.bci.evaluation.service.impl.UserProcessServiceImpl;

class UserProccessServiceImplTest {

    private static final String TOKEN="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1biIsImV4cCI6MTYyMzI5MDc1MCwiaWF0IjoxNjIzMjU0NzUwfQ.-LCPXqKn452sGTmiiBjTklSor_rLrfobZdilxFg-aiY";
    
    @InjectMocks
    private UserProcessServiceImpl userProcessServiceImpl;
    
    @Mock
    private UserRepository userRepository;
    
    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }
    
    @DisplayName("Test Mock UserProcessServiceImpl")
    @Test
    void userPersistanceProcessTest(){
        
        when(userRepository.findUserByMail(Mockito.any())).thenReturn(null);
        when(userRepository.save(Mockito.any())).thenReturn(dummyUserRepository());
        UserResponseDto result = userProcessServiceImpl.userPersistanceProcess(dummyUserRequest(), TOKEN);
        
        assertEquals(TOKEN.substring(7), result.getToken());
        
    }
    
    @DisplayName("Test Mock userExceptionTest")
    @Test
    void userRegisteredTest(){
        
        when(userRepository.findUserByMail(Mockito.any())).thenReturn(dummyUserRepository());
        UserRequestDto dto=dummyUserRequest();
        Assertions.assertThrows(UserException.class, () -> userProcessServiceImpl.userPersistanceProcess(dto, TOKEN));
        
    }
    
    @DisplayName("Test Mock userExceptionTest")
    @Test
    void userExceptionPasswordTest(){
        
        when(userRepository.findUserByMail(Mockito.any())).thenReturn(dummyUserRepository());
        UserRequestDto dto=dummyUserRequest();
        dto.setPassword("badPasword");
        
        Assertions.assertThrows(UserException.class, () -> userProcessServiceImpl.userPersistanceProcess(dto, TOKEN));
        
    }
    
    @DisplayName("Test Mock userExceptionEmailTest")
    @Test
    void userExceptionEmailTest(){
        
        when(userRepository.findUserByMail(Mockito.any())).thenReturn(dummyUserRepository());
        UserRequestDto dto=dummyUserRequest();
        dto.setEmail("badEmail");
        
        Assertions.assertThrows(UserException.class, () -> userProcessServiceImpl.userPersistanceProcess(dto, TOKEN));
        
    }
    
    private UserRequestDto dummyUserRequest() {
        
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setEmail("otrousuario@undominnio.com");
        userRequestDto.setName("Un Usuario");
        userRequestDto.setPassword("Somepassword45");
        
        PhonesDto phonesDto=new PhonesDto();
        phonesDto.setCitycode("45");
        phonesDto.setContrycode("52");
        phonesDto.setNumber("12345678");
        List<PhonesDto> phonesDtos=new ArrayList<>();
        phonesDtos.add(phonesDto);
        userRequestDto.setPhones(phonesDtos);
        
        return userRequestDto;
    }
    
    private User dummyUserRepository() {
        
        User user=new User();
        user.setUserId(UUID.randomUUID());
        user.setActive(true);
        user.setCreated(new Date());
        user.setLastLogin(new Date());
        user.setModified(new Date());
        user.setPassword("Somepassword45");
        user.setEmail("unusuario@undominnio.com");
        user.setName("someUser");
        Phone phones=new Phone();
        phones.setCityCode("45");
        phones.setContryCode("52");
        phones.setNumber("12345678");
        List<Phone> phoneList=new ArrayList<>();
        phoneList.add(phones);
        user.setPhones(phoneList);
        
        return user;
    }
}
