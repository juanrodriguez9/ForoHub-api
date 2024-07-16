package com.forohub.forohub_api.infra.errors;

public class IntegrityValidation extends RuntimeException{
    public IntegrityValidation(String msg){
        super(msg);
    }
}
