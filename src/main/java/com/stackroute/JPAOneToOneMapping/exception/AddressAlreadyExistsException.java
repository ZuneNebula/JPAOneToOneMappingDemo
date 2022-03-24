package com.stackroute.JPAOneToOneMapping.exception;

public class AddressAlreadyExistsException extends Exception{
    public AddressAlreadyExistsException(String message) {
        super(message);
    }
}
