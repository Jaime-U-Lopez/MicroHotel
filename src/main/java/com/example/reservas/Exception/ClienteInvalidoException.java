package com.example.reservas.Exception;


import org.springframework.web.bind.annotation.ExceptionHandler;


public class ClienteInvalidoException extends RuntimeException {



    public ClienteInvalidoException(String message) {
        super(message);
    }



}
