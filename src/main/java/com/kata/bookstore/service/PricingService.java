package com.kata.bookstore.service;

import com.kata.bookstore.book.Book;
import com.kata.bookstore.constants.AppConstants;
import java.util.List;

public class PricingService {

    public double calculate(List<Book> books) {

        if (books == null || books.isEmpty()) {
            return 0.0;
        }

        int uniqueCount = (int) books.stream().distinct().count();

        double total = books.size() * AppConstants.BASE_BOOK_PRICE;

        if (uniqueCount == 2) {
            return total * 0.95; // 5% discount
        }

        return total;
    }
}
