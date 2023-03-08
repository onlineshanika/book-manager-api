package com.techreturners.bookmanager.dto;

import lombok.Getter;

import java.util.Date;

public class Error {

    @Getter
    String errorMessage ;

    @Getter
    Date timestamp ;

    public Error(String errorMessage, Date timestamp) {
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }
}
