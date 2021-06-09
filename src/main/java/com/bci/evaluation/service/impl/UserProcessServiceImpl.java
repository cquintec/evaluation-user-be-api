package com.bci.evaluation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bci.evaluation.domain.UserRequestDto;
import com.bci.evaluation.domain.UserResponseDto;
import com.bci.evaluation.entities.Phone;
import com.bci.evaluation.entities.User;
import com.bci.evaluation.exception.UserException;
import com.bci.evaluation.repository.UserRepository;
import com.bci.evaluation.service.UserProcessService;
import com.bci.evaluation.utils.GlobalConstants;

@Service
public class UserProcessServiceImpl implements UserProcessService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserResponseDto userPersistanceProcess(UserRequestDto userRequest, String authorizationHeader) {
        

        validationProcess(userRequest);
        
        if (validateEmailExist(userRequest.getEmail())) {
            throw new UserException(GlobalConstants.MAIL_ALREADY_EXIST);
        }
        
        User user=saveNewUser(userRequest);
        UserResponseDto userResponseDto = parseResponse(authorizationHeader, user);
        
        return userResponseDto;
    }

    private UserResponseDto parseResponse(String authorizationHeader, User user) {
        UserResponseDto userResponseDto=new UserResponseDto();
        userResponseDto.setId(user.getUserId().toString());
        userResponseDto.setToken(authorizationHeader.substring(7));
        userResponseDto.setCreated(user.getCreated());
        userResponseDto.setLastLogin(user.getLastLogin());
        userResponseDto.setModified(user.getModified());
        userResponseDto.setActive(user.isActive());
        return userResponseDto;
    }

    private User saveNewUser(UserRequestDto userRequest) {

        User user=new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setCreated(new Date());
        user.setLastLogin(new Date());
        user.setModified(new Date());
        user.setActive(true);
        
        List<Phone> phones=new ArrayList<>();
        
        userRequest.getPhones().stream().forEach(x->{
            
            Phone phone =new Phone();
            phone.setCityCode(x.getCitycode());
            phone.setContryCode(x.getContrycode());
            phone.setNumber(x.getNumber());
            phone.setUser(user);
            phones.add(phone);
        });
        
        user.setPhones(phones);
        
        return userRepository.save(user);
        
    }

    private void validationProcess(UserRequestDto userRequest) {
        
        if (!validationByRegex(GlobalConstants.EMAIL_REGEX,userRequest.getEmail())) {
            throw new UserException(GlobalConstants.EMAIL_ERROR_REGEX);
        }else if (!validationByRegex(GlobalConstants.PASSWORD_REGEX,userRequest.getPassword())) {
            throw new UserException(GlobalConstants.PASSWORD_REGEX_ERROR);
        }
    }
    
    private boolean validationByRegex(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher mather = pattern.matcher(value);
        return mather.find();
    }
    
    private boolean validateEmailExist(String email) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByMail(email));
        return optionalUser.isPresent();
    }

}
