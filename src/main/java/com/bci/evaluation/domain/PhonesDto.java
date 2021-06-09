package com.bci.evaluation.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class PhonesDto implements Serializable{

    private static final long serialVersionUID = 1L;
    private String number;
    private String citycode;
    private String contrycode;
    
}
