package com.bci.evaluation.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UserRequestDto implements Serializable{

    private static final long serialVersionUID = 1L;
   
    @NotNull
    private String name;

    @Email(regexp =".+@.+\\..+")
    private String email;
    
    @NotNull
    private String password;
    
    @NotNull
    private List<PhonesDto> phones;
    
    
}
