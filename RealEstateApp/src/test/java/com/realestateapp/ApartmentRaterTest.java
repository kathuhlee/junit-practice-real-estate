package com.realestateapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRaterTest {

    @ParameterizedTest
    @CsvSource({"72.0, 250000.0, 0", "48.0, 350000.0, 1", "30.0, 600000.0, 2"})
    void shouldReturnCorrectRatingWhenCorrectApartment(Double area, Double price, int rating) {
        Apartment apartment = new Apartment(area, new BigDecimal(price));
        int actual = ApartmentRater.rateApartment(apartment);
        Assertions.assertEquals(rating, actual);

    }

    @Test
    void shouldReturnErrorValueWhenIncorrectApartment() {
        Apartment apartment = new Apartment(0.0, new BigDecimal(350000));
        int expected = -1;
        int actual = ApartmentRater.rateApartment(apartment);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    void shouldCalculateAverageRatingWhenCorrectAptList() {
        List<Apartment> apartments = new ArrayList<>();
        apartments.add(new Apartment(72.0, new BigDecimal(250000.0)));
        apartments.add(new Apartment(48.0, new BigDecimal(350000.0)));
        apartments.add(new Apartment(30.0, new BigDecimal(600000.0)));
        double expected = 1.0;
        double actual = ApartmentRater.calculateAverageRating(apartments);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionInCalculatingAverageRatingWhenEmptyApartmentList() {
        List<Apartment> apartments = new ArrayList<>();
        Executable executable = () -> {
            ApartmentRater.calculateAverageRating(apartments);
        };
        Assertions.assertThrows(RuntimeException.class, executable);
    }
}