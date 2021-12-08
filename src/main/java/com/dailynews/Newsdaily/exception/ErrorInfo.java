package com.dailynews.Newsdaily.exception;
import java.util.List;

public class ErrorInfo {
    private List<String> errors;

    public ErrorInfo(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}