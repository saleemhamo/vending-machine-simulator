package com.example.vending_machine_simulator.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize
public class APIResponse {
    private Object result;

    public APIResponse() {
        super();
    }

    public APIResponse(Object result) {
        super();
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "APIResponse [result=" + result + "]";
    }

}
