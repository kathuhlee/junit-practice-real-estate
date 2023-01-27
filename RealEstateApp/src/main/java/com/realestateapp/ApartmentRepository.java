package com.realestateapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApartmentRepository {
    private List<Apartment> apartments = new ArrayList();

    public ApartmentRepository() {
    }

    public List<Apartment> getAllApartments() {
        return this.apartments;
    }

    public void addApartment(Apartment apartment) {
        this.apartments.add(apartment);
    }

    public List<Apartment> findApartmentsBySize(double min, double max) {
        return (List)this.apartments.stream().filter((apartment) -> {
            return apartment.getArea() >= min;
        }).filter((apartment) -> {
            return apartment.getArea() <= max;
        }).collect(Collectors.toList());
    }
}
