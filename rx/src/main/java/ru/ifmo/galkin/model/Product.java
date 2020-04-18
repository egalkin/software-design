package ru.ifmo.galkin.model;

import org.springframework.data.annotation.Id;
import javax.annotation.Nonnull;
import java.beans.ConstructorProperties;

public class Product {

    @Id
    private final long id;

    private final String name;

    private final Currency currency;

    private final double price;

    @ConstructorProperties({"id", "name", "currency", "price"})
    public Product(long id, @Nonnull String name, @Nonnull Currency currency, double price) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", currency=" + currency +
                ", price=" + price +
                '}';
    }
}
