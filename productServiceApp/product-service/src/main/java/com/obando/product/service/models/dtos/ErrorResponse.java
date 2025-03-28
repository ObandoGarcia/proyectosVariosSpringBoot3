package com.obando.product.service.models.dtos;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private String code;
    private HttpStatus status;
    private String message;
    private List<String> detailsMessages;
    private LocalDateTime timeStamp;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timeStamp, String message, HttpStatus status, String code, List<String> detailsMessages) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.status = status;
        this.code = code;
        this.detailsMessages = detailsMessages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetailsMessages() {
        return detailsMessages;
    }

    public void setDetailsMessages(List<String> detailsMessages) {
        this.detailsMessages = detailsMessages;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
