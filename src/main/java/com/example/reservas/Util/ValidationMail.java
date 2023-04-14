package com.example.reservas.Util;

import java.util.regex.Pattern;

public class ValidationMail {

    public static boolean esMailValido(String email) {
        // Expresión regular para validar correos electrónicos
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        // Compilar la expresión regular en un patrón
        Pattern pattern = Pattern.compile(regex);

        //validar el correo electronico
        return pattern.matcher(email).matches();
    }



}
