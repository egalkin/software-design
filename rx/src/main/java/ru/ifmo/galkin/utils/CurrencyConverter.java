package ru.ifmo.galkin.utils;

import ru.ifmo.galkin.model.Currency;
import ru.ifmo.galkin.model.Product;

import java.util.HashMap;

public class CurrencyConverter {

    private static HashMap<String, Double> converterMap = new HashMap<>();

    static {
        converterMap.put("RUBUSD", 0.0136986);
        converterMap.put("USDRUB", 73.65);
        converterMap.put("RUBEUR", 0.0124984);
        converterMap.put("EURRUB", 80.01);
        converterMap.put("EURUSD", 1.09);
        converterMap.put("USDEUR", 0.92);
    }

    public static Product convert(Product product, Currency neededCurrency) {
        Double newPrice = convertPrice(product.getPrice(), product.getCurrency(), neededCurrency);
        return new Product(product.getId(), product.getName(), neededCurrency, newPrice);
    }

    private static Double convertPrice(Double price, Currency from, Currency to) {
        return price * converterMap.get(String.format("%s%s", from, to));
    }

}
