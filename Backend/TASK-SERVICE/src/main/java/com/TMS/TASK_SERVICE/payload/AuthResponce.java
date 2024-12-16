package com.TMS.TASK_SERVICE.payload;

public class AuthResponce {
    String message;

    public AuthResponce() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuthResponce(String message) {
        this.message = message;
    }
}
