package com.bci.evaluation.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationRS implements Serializable{

    private static final long serialVersionUID = 1L;

    private final String jwt;

}
