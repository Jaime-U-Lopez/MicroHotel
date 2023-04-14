package com.example.reservas.Exception;

public class DataNotFoundException  extends RuntimeException{

    public DataNotFoundException(){};


    public DataNotFoundException(String message){

        super(message);
    }

}
