package com.raffler.mscreditrating.application.ex;

import lombok.Getter;

public class BadComunicationWithMSException extends Exception{
    @Getter
    private Integer status;
    public BadComunicationWithMSException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
