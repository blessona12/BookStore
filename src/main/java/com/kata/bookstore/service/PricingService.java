package com.kata.bookstore.service;

import com.kata.bookstore.book.Book;
import com.kata.bookstore.constants.AppConstants;
import java.util.List;

public class PricingService {

    public double calculate(List<Book> books) {

        return books.size() * AppConstants.BASE_BOOK_PRICE;
    }
}
