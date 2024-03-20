package com.livecoding.estudos.Errors;

public class ErrorResponse {
    private String message;
    // Adicione outros campos, se necessário

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}