package com.bci.evaluation.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse implements Serializable{

    private static final long serialVersionUID = 1L;
    private String mensaje;
    
}
