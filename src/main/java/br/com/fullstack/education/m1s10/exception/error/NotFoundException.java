package br.com.fullstack.education.m1s10.exception.error;

public abstract class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }
    public NotFoundException(String message) {
        super(message);
    }
}
