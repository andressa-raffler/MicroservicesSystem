package com.raffler.mscreditrating.application.ex;

public class ClientDataNotFoundException extends Exception{
    public ClientDataNotFoundException() {
        super("CPF not found");
    }
}
