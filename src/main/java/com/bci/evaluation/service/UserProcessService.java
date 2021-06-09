package com.bci.evaluation.service;

import com.bci.evaluation.domain.UserRequestDto;
import com.bci.evaluation.domain.UserResponseDto;

public interface UserProcessService {

    UserResponseDto userPersistanceProcess(UserRequestDto userRequest, String authorizationHeader);
}
