package com.rafael.gvendas.gestaovendas.exceptions;

public class Error {

    private final String userMessage;
    private final String developerMessage;
    public Error(String userMessage, String developerMessage) {
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
