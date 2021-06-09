package com.bci.evaluation.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserResponseDto implements Serializable{

    private static final long serialVersionUID = 1L;
    private String id;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;
    
}
