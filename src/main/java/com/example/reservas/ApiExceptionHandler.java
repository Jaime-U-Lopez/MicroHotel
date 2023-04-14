package com.example.reservas;


import com.example.reservas.Dto.ApiException;
import com.example.reservas.Exception.ClienteInvalidoException;
import com.example.reservas.Exception.DataNotFoundException;
import com.example.reservas.Exception.HabitacionValidoException;
import com.example.reservas.Exception.ReservaInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {



    @ExceptionHandler(  value={ ClienteInvalidoException.class, HabitacionValidoException.class, ReservaInvalidoException.class})
    public ResponseEntity<Object> handleDataNotFoundException(RuntimeException ex  ) {

        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))

        );

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);

    }

/*
    @ExceptionHandler(value={ClienteInvalidoException.class, HabitacionValidoException.class, ReservaInvalidoException.class})
    public ResponseEntity<Object> handleDataNotFoundException2(ClienteInvalidoException ex, HabitacionValidoException e, ReservaInvalidoException re) {

        ApiException apiException = null;
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (ex != null) {
            apiException = new ApiException(
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
        } else if (e != null) {
            apiException = new ApiException(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
        } else if (re != null) {
            apiException = new ApiException(
                    re.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
        } else {
            // Si no se reconoce la excepción, se crea una instancia de ApiException genérica
            apiException = new ApiException(
                    "Error desconocido",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ZonedDateTime.now(ZoneId.of("Z"))
            );
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(apiException, httpStatus);
    }
/*

 */

}
