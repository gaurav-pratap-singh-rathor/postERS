package com.gaurav.rest.webservices.postERS.exception;
import java.time.LocalDateTime;

public class ErrorDetails { // It will fetch the error detail from program and display it to the browser

    private LocalDateTime timestamp;
    private String message;
    private String details;


    //Constructor
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }


}