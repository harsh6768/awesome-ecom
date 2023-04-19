package com.ecom.nabula.utils;

import java.util.List;

public class ErrorResponse {
    private List<String> errors;

    public ErrorResponse(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
