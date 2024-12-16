package com.TSM.USER_SERVICE.payloads;


public class ApiResponse {
    private String message;

    public String getMessage() {
        return message;
    }
    public ApiResponse(){};
    public ApiResponse(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
