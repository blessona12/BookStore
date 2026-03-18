package com.kata.bookstore.service;

import com.kata.bookstore.book.Book;
import com.kata.bookstore.constants.AppConstants;
import java.util.*;

public class PricingService {

    private static final Map<Integer, Double> DISCOUNT_MAP = Map.of(
            1, 1.0,
            2, 0.95,
            3, 0.90,
            4, 0.80,
            5, 0.75
    );

    public double calculate(List<Book> books) {

        if (books == null || books.isEmpty()) {
            return 0.0;
        }

        Map<Book, Integer> bookCount = countBooks(books);

        List<Integer> groups = buildGroups(bookCount);

        groups = optimizeGroups(groups);

        return calculateTotal(groups);
    }

    // Step 1: Count books
    private Map<Book, Integer> countBooks(List<Book> books) {
        Map<Book, Integer> countMap = new HashMap<>();
        for (Book book : books) {
            countMap.put(book, countMap.getOrDefault(book, 0) + 1);
        }
        return countMap;
    }

    // Step 2: Create groups of unique books
    private List<Integer> buildGroups(Map<Book, Integer> bookCount) {
        List<Integer> groups = new ArrayList<>();

        while (!bookCount.isEmpty()) {
            int uniqueSetSize = 0;

            Iterator<Map.Entry<Book, Integer>> iterator = bookCount.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Book, Integer> entry = iterator.next();

                uniqueSetSize++;

                if (entry.getValue() == 1) {
                    iterator.remove();
                } else {
                    entry.setValue(entry.getValue() - 1);
                }
            }

            groups.add(uniqueSetSize);
        }

        return groups;
    }

    // Step 3: Optimize (5+3 → 4+4)
    private List<Integer> optimizeGroups(List<Integer> groups) {

        while (groups.contains(5) && groups.contains(3)) {
            groups.remove(Integer.valueOf(5));
            groups.remove(Integer.valueOf(3));

            groups.add(4);
            groups.add(4);
        }

        return groups;
    }

    // Step 4: Calculate final price
    private double calculateTotal(List<Integer> groups) {
        double total = 0.0;

        for (int size : groups) {
            double price = size * AppConstants.BASE_BOOK_PRICE;
            double multiplier = DISCOUNT_MAP.get(size);
            total += price * multiplier;
        }

        return total;
    }
}
