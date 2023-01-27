package com.realestateapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RandomApartmentGeneratorTest {
    private static final double MAX_MULTIPLIER = 4.0;

    RandomApartmentGeneratorTest() {
    }

    @Nested
    class GeneratorCustomParamsTests {
        private RandomApartmentGenerator generator;
        private double minArea = 15.0;
        private BigDecimal minPricePerSquareMeter = new BigDecimal(5000.0);

        GeneratorCustomParamsTests() {
        }

        @BeforeEach
        void setup() {
            this.generator = new RandomApartmentGenerator(this.minArea, this.minPricePerSquareMeter);
        }

        @RepeatedTest(10)
        void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
            double minArea = this.minArea;
            double maxArea = minArea * 4.0;
            BigDecimal minPricePerSquareMeter = this.minPricePerSquareMeter;
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(4.0));
            Apartment apartment = this.generator.generate();
            BigDecimal maxApartmentPrice = (new BigDecimal(apartment.getArea())).multiply(maxPricePerSquareMeter);
            BigDecimal minApartmentPrice = (new BigDecimal(apartment.getArea())).multiply(minPricePerSquareMeter);
            Assertions.assertAll(new Executable[]{() -> {
                Assertions.assertTrue(apartment.getArea() >= minArea);
            }, () -> {
                Assertions.assertTrue(apartment.getArea() <= maxArea);
            }, () -> {
                Assertions.assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0);
            }, () -> {
                Assertions.assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0);
            }});
        }
    }

    @Nested
    class GeneratorDefaultParamsTests {
        private RandomApartmentGenerator generator;

        GeneratorDefaultParamsTests() {
        }

        @BeforeEach
        void setup() {
            this.generator = new RandomApartmentGenerator();
        }

        @RepeatedTest(10)
        void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
            double minArea = 30.0;
            double maxArea = minArea * 4.0;
            BigDecimal minPricePerSquareMeter = new BigDecimal(3000.0);
            BigDecimal maxPricePerSquareMeter = minPricePerSquareMeter.multiply(new BigDecimal(4.0));
            Apartment apartment = this.generator.generate();
            BigDecimal maxApartmentPrice = (new BigDecimal(apartment.getArea())).multiply(maxPricePerSquareMeter);
            BigDecimal minApartmentPrice = (new BigDecimal(apartment.getArea())).multiply(minPricePerSquareMeter);
            Assertions.assertAll(new Executable[]{() -> {
                Assertions.assertTrue(apartment.getArea() >= minArea);
            }, () -> {
                Assertions.assertTrue(apartment.getArea() <= maxArea);
            }, () -> {
                Assertions.assertTrue(apartment.getPrice().compareTo(minApartmentPrice) >= 0);
            }, () -> {
                Assertions.assertTrue(apartment.getPrice().compareTo(maxApartmentPrice) <= 0);
            }});
        }
    }
}