package ru.ifmo.egalkin.exception;

public class AccountUniqunessException extends RuntimeException {
    public AccountUniqunessException() {
        super("Account with given id already exists.");
    }
}
