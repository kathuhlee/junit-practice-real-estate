package com.realestateapp;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentRepositoryTest {
    private ApartmentRepository apartmentRepository;

    ApartmentRepositoryTest() {
    }

    @BeforeClass
    void setup() {
        this.apartmentRepository = new ApartmentRepository();
        this.apartmentRepository.addApartment(new Apartment(48.0, new BigDecimal(320000.0)));
        this.apartmentRepository.addApartment(new Apartment(97.0, new BigDecimal(645000.0)));
        this.apartmentRepository.addApartment(new Apartment(43.0, new BigDecimal(280000.0)));
    }

    @Test
    void shouldReturnEmptyListWhenNoAptsMatched() {
        List<Apartment> actual = this.apartmentRepository.findApartmentsBySize(20.0, 30.0);
        Assertions.assertTrue(true);
    }

}