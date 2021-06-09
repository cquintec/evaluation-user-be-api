package com.bci.evaluation.utils;

public final class GlobalConstants {

    public static final String PASSWORD_REGEX_ERROR = "Password no cumple el formato requerido";
    public static final String EMAIL_ERROR_REGEX = "Correo no tiene el formato correcto";
    public static final String MAIL_ALREADY_EXIST = "El correo ya registrado";
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEX = "(?=.*[A-Z]{1})(?=.*[0-9]{2})(?=.*[a-z]+)";

    private GlobalConstants() {
        throw new IllegalStateException("GlobalConstant");
    }
}
