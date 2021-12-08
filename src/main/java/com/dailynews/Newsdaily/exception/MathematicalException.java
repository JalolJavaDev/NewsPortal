package com.dailynews.Newsdaily.exception;
public class MathematicalException extends RuntimeException {
    private String error;

    public static MathematicalException createWith(String error) {
        return new MathematicalException(error);
    }

    public MathematicalException(String error) {
        this.error = error;
    }

    @Override
    public String getMessage() {
        return ".........***** '" + error + "'*****.........";
    }
}