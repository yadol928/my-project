package com.example.DB_start.exception;

//Ошибка, возникающая, когда объект не найден
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
