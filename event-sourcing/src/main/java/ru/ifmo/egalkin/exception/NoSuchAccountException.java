package ru.ifmo.egalkin.exception;

public class NoSuchAccountException extends RuntimeException{
    public NoSuchAccountException() {
        super("No account with such id presented");
    }
}
