package ru.ifmo.egalkin.exception;

public class AccountExpiringException extends RuntimeException{
    public AccountExpiringException() {
        super("Can't expire account");
    }
}
