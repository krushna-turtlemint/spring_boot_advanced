package com.mrmk.restfulwebservices.exeception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails   {
    private String message;
    private  String details;
    private LocalDate timeStamp;

    public ErrorDetails(LocalDate timeStamp, String message, String details) {
        super();
        this.message = message;
        this.details = details;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }
}
