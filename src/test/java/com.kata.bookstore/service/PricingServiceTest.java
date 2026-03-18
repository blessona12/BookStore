package com.kata.bookstore.service;

import com.kata.bookstore.book.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PricingServiceTest {

    @Test
    void shouldReturn50ForSingleBook() {

        PricingService service = new PricingService();

        double price = service.calculate(List.of(Book.CLEAN_CODE));

        assertThat(price).isEqualTo(50.0);
    }

    @Test
    void shouldApply5PercentDiscountForTwoDifferentBooks() {

        PricingService service = new PricingService();

        double price = service.calculate(
                List.of(Book.CLEAN_CODE, Book.THE_CLEAN_CODER)
        );

        assertThat(price).isEqualTo(95.0);
    }

    @Test
    void shouldApply10PercentDiscountForThreeDifferentBooks() {

        PricingService service = new PricingService();

        double price = service.calculate(
                List.of(
                        Book.CLEAN_CODE,
                        Book.THE_CLEAN_CODER,
                        Book.CLEAN_ARCHITECTURE
                )
        );

        assertThat(price).isEqualTo(135.0); // 150 - 10%
    }
}
