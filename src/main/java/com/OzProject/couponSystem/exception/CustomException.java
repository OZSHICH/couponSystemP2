package com.OzProject.couponSystem.exception;

public class CustomException extends Exception {

    public CustomException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
