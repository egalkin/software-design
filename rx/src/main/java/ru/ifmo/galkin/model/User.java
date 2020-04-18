package ru.ifmo.galkin.model;

import java.beans.ConstructorProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nonnull;


@Document
public class User {

    @Id
    private final long id;

    private final String username;

    private final Currency accountCurrency;

    @ConstructorProperties({"id", "username", "accountCurrency"})
    public User(long id, @Nonnull String username, @Nonnull Currency accountCurrency) {
        this.id = id;
        this.username = username;
        this.accountCurrency = accountCurrency;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Currency getAccountCurrency() {
        return accountCurrency;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", accountCurrency=" + accountCurrency +
                '}';
    }
}
