package com.kata.bookstore.service;

import com.kata.bookstore.book.Book;
import com.kata.bookstore.constants.AppConstants;
import java.util.List;
import java.util.Map;

public class PricingService {

    private static final Map<Integer, Double> DISCOUNT_MAP = Map.of(
            1, 1.0,
            2, 0.95,
            3, 0.90,
            4, 0.80
    );

    public double calculate(List<Book> books) {

        if (books == null || books.isEmpty()) {
            return 0.0;
        }

        int uniqueCount = (int) books.stream().distinct().count();

        double total = books.size() * AppConstants.BASE_BOOK_PRICE;

        double multiplier = DISCOUNT_MAP.getOrDefault(uniqueCount, 1.0);

        return total * multiplier;
    }
}
