package ru.ifmo.egalkin.exception;

public class EnterRejectedException extends RuntimeException {
    public EnterRejectedException() {
        super("Enter rejected for this account");
    }
}
